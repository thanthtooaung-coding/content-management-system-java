// File: src/main/java/com/jetstream/learningmanagementservice/controller/CourseAdminController.java
package com.jetstream.learningmanagementservice.controller;

import com.jetstream.learningmanagementservice.request.CourseRequest;
import com.jetstream.learningmanagementservice.request.MaterialRequest;
import com.jetstream.learningmanagementservice.response.CourseResponse;
import com.jetstream.learningmanagementservice.response.MaterialResponse;
import com.jetstream.learningmanagementservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lms/admin/courses")
@RequiredArgsConstructor
public class CourseAdminController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest request) {
        return new ResponseEntity<>(courseService.createCourse(request), HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequest request) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, request));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{courseId}/materials")
    public ResponseEntity<MaterialResponse> addMaterial(@PathVariable Long courseId, @RequestBody MaterialRequest request) {
        return new ResponseEntity<>(courseService.addMaterialToCourse(courseId, request), HttpStatus.CREATED);
    }
}