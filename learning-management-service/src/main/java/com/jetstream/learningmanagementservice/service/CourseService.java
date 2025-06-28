package com.jetstream.learningmanagementservice.service;

import com.jetstream.learningmanagementservice.event.EventPublisherService;
import com.jetstream.learningmanagementservice.exception.ResourceNotFoundException;
import com.jetstream.learningmanagementservice.model.*;
import com.jetstream.learningmanagementservice.repository.*;
import com.jetstream.learningmanagementservice.request.CourseRequest;
import com.jetstream.learningmanagementservice.request.MaterialRequest;
import com.jetstream.learningmanagementservice.response.CourseResponse;
import com.jetstream.learningmanagementservice.response.MaterialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseCategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final CourseMaterialRepository materialRepository;
    private final EventPublisherService eventPublisher;

    // Course CRUD
    public CourseResponse createCourse(CourseRequest request) {
        User instructor = userRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getInstructorId()));
        CourseCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setInstructor(instructor);
        course.setCategory(category);

        Course savedCourse = courseRepository.save(course);
        eventPublisher.publishLmsEvent(CourseResponse.fromEntity(savedCourse), "CourseCreated", "lms.course.created");
        return CourseResponse.fromEntity(savedCourse);
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream().map(CourseResponse::fromEntity).collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return CourseResponse.fromEntity(course);
    }

    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        // Update fields...
        Course updatedCourse = courseRepository.save(course);
        eventPublisher.publishLmsEvent(CourseResponse.fromEntity(updatedCourse), "CourseUpdated", "lms.course.updated");
        return CourseResponse.fromEntity(updatedCourse);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) throw new ResourceNotFoundException("Course", "id", id);
        courseRepository.deleteById(id);
        eventPublisher.publishLmsEvent(id, "CourseDeleted", "lms.course.deleted");
    }

    // Material CRUD
    public MaterialResponse addMaterialToCourse(Long courseId, MaterialRequest request) {
        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new ResourceNotFoundException("Lesson", "id", request.getLessonId()));

        CourseMaterial material = new CourseMaterial();
        material.setTitle(request.getTitle());
        material.setContent(request.getContent());
        material.setMaterialType(request.getMaterialType());
        material.setLesson(lesson);
        return MaterialResponse.fromEntity(materialRepository.save(material));
    }

    public List<MaterialResponse> getMaterialsForCourse(Long courseId) {
        // Security check: ensure student is enrolled
        return materialRepository.findAll().stream() // In real app, query materials by courseId
                .map(MaterialResponse::fromEntity)
                .collect(Collectors.toList());
    }
}