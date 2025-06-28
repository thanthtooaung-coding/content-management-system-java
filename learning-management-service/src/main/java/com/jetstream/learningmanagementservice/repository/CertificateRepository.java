package com.jetstream.learningmanagementservice.repository;

import com.jetstream.learningmanagementservice.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}