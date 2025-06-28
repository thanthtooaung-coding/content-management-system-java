package com.jetstream.learningmanagementservice.response;

import com.jetstream.learningmanagementservice.model.Enrollment;
import com.jetstream.learningmanagementservice.model.EnrollmentStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private Long courseId;
    private String courseTitle;
    private LocalDateTime enrollmentDate;
    private EnrollmentStatus status;

    public static EnrollmentResponse fromEntity(Enrollment enrollment) {
        EnrollmentResponse dto = new EnrollmentResponse();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseTitle(enrollment.getCourse().getTitle());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());
        return dto;
    }
}