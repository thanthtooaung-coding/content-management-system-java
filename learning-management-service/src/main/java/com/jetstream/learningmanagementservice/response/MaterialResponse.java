package com.jetstream.learningmanagementservice.response;

import com.jetstream.learningmanagementservice.model.CourseMaterial;
import com.jetstream.learningmanagementservice.model.MaterialType;
import lombok.Data;

@Data
public class MaterialResponse {
    private Long id;
    private String title;
    private String content;
    private MaterialType materialType;

    public static MaterialResponse fromEntity(CourseMaterial material) {
        MaterialResponse dto = new MaterialResponse();
        dto.setId(material.getId());
        dto.setTitle(material.getTitle());
        dto.setContent(material.getContent());
        dto.setMaterialType(material.getMaterialType());
        return dto;
    }
}