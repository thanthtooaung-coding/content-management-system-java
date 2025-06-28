package com.jetstream.learningmanagementservice.response;

import com.jetstream.learningmanagementservice.model.CourseCategory;
import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;

    public static CategoryResponse fromEntity(CourseCategory category) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}