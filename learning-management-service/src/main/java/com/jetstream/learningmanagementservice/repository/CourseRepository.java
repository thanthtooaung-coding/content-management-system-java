package com.jetstream.learningmanagementservice.repository;

import com.jetstream.learningmanagementservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}