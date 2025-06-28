package com.jetstream.learningmanagementservice.controller;

import com.jetstream.learningmanagementservice.request.EnrollmentRequest;
import com.jetstream.learningmanagementservice.response.CertificateResponse;
import com.jetstream.learningmanagementservice.response.EnrollmentResponse;
import com.jetstream.learningmanagementservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> enroll(@RequestBody EnrollmentRequest request) {
        return new ResponseEntity<>(enrollmentService.enrollUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/{enrollmentId}/generate-certificate")
    public ResponseEntity<CertificateResponse> generateCertificate(@PathVariable Long enrollmentId) {
        return new ResponseEntity<>(enrollmentService.generateCertificate(enrollmentId), HttpStatus.CREATED);
    }
}