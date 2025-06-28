package com.jetstream.learningmanagementservice.service;

import com.jetstream.learningmanagementservice.exception.ResourceNotFoundException;
import com.jetstream.learningmanagementservice.model.CourseCategory;
import com.jetstream.learningmanagementservice.repository.CourseCategoryRepository;
import com.jetstream.learningmanagementservice.request.CategoryRequest;
import com.jetstream.learningmanagementservice.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CourseCategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryResponse::fromEntity).collect(Collectors.toList());
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        CourseCategory category = new CourseCategory();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        CourseCategory savedCategory = categoryRepository.save(category);
        return CategoryResponse.fromEntity(savedCategory);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        CourseCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        CourseCategory updatedCategory = categoryRepository.save(category);
        return CategoryResponse.fromEntity(updatedCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category", "id", id);
        }
        categoryRepository.deleteById(id);
    }
}