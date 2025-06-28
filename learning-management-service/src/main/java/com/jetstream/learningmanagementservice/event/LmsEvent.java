package com.jetstream.learningmanagementservice.event;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LmsEvent<T> implements Serializable {
    private String eventType;
    private T data;
    private Instant timestamp;
}