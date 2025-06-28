package com.jetstream.learningmanagementservice.response;

import com.jetstream.learningmanagementservice.model.Certificate;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CertificateResponse {
    private Long id;
    private Long enrollmentId;
    private LocalDateTime issueDate;
    private String certificateUrl;

    public static CertificateResponse fromEntity(Certificate certificate) {
        CertificateResponse dto = new CertificateResponse();
        dto.setId(certificate.getId());
        dto.setEnrollmentId(certificate.getEnrollment().getId());
        dto.setIssueDate(certificate.getIssueDate());
        dto.setCertificateUrl(certificate.getCertificateUrl());
        return dto;
    }
}