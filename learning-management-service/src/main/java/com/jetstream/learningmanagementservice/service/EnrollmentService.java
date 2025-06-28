package com.jetstream.learningmanagementservice.service;

import com.jetstream.learningmanagementservice.event.EventPublisherService;
import com.jetstream.learningmanagementservice.exception.ResourceNotFoundException;
import com.jetstream.learningmanagementservice.model.*;
import com.jetstream.learningmanagementservice.repository.*;
import com.jetstream.learningmanagementservice.request.EnrollmentRequest;
import com.jetstream.learningmanagementservice.response.CertificateResponse;
import com.jetstream.learningmanagementservice.response.EnrollmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CertificateRepository certificateRepository;
    private final EventPublisherService eventPublisher;

    public EnrollmentResponse enrollUser(EnrollmentRequest request) {
        User student = userRepository.findById(request.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getStudentId()));
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course", "id", request.getCourseId()));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.ENROLLED);
        
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        eventPublisher.publishLmsEvent(EnrollmentResponse.fromEntity(savedEnrollment), "UserEnrolled", "lms.enrollment.created");
        return EnrollmentResponse.fromEntity(savedEnrollment);
    }

    public CertificateResponse generateCertificate(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", enrollmentId));
        // TODO: Add logic to verify enrollment is COMPLETED
        
        Certificate certificate = new Certificate();
        certificate.setEnrollment(enrollment);
        certificate.setIssueDate(LocalDateTime.now());
        certificate.setCertificateUrl("/api/lms/certificates/" + certificate.getId()); // Placeholder URL
        
        Certificate savedCertificate = certificateRepository.save(certificate);
        return CertificateResponse.fromEntity(savedCertificate);
    }
    
    public CertificateResponse getCertificate(Long id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Certificate", "id", id));
        return CertificateResponse.fromEntity(certificate);
    }
}