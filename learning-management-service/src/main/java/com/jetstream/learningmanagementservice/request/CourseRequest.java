package com.jetstream.learningmanagementservice.request;

import lombok.Data;
import java.math.BigDecimal;
@Data

public class CourseRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private Long instructorId;
}