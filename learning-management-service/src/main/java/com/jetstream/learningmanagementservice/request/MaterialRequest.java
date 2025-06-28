package com.jetstream.learningmanagementservice.request;

import com.jetstream.learningmanagementservice.model.MaterialType;
import lombok.Data;

@Data
public class MaterialRequest {
    private String title;
    private String content;
    private MaterialType materialType;
    private Long lessonId;
}