package com.example.restapi.dto;

import lombok.Builder;

@Builder
public record CreateCourseRequest(
        String code,
        String title,
        Double price
) {
}
