// File: src/main/java/com/jetstream/learningmanagementservice/controller/CourseController.java
package com.jetstream.learningmanagementservice.controller;

import com.jetstream.learningmanagementservice.response.CourseResponse;
import com.jetstream.learningmanagementservice.response.MaterialResponse;
import com.jetstream.learningmanagementservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lms/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @GetMapping("/{courseId}/materials")
    public ResponseEntity<List<MaterialResponse>> getMaterialsForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getMaterialsForCourse(courseId));
    }
}