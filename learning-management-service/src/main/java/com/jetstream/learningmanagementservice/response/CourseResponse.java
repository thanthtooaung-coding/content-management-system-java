package com.jetstream.learningmanagementservice.response;

import com.jetstream.learningmanagementservice.model.Course;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String category;
    private String instructorName;

    public static CourseResponse fromEntity(Course course) {
        CourseResponse dto = new CourseResponse();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        if (course.getCategory() != null) {
            dto.setCategory(course.getCategory().getName());
        }
        if (course.getInstructor() != null) {
            dto.setInstructorName(course.getInstructor().getName());
        }
        return dto;
    }
}