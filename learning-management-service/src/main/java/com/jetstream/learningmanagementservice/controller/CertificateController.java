package com.jetstream.learningmanagementservice.controller;

import com.jetstream.learningmanagementservice.response.CertificateResponse;
import com.jetstream.learningmanagementservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {
    
    private final EnrollmentService enrollmentService;

    @GetMapping("/{certificateId}")
    public ResponseEntity<CertificateResponse> getCertificate(@PathVariable Long certificateId) {
        return ResponseEntity.ok(enrollmentService.getCertificate(certificateId));
    }
}