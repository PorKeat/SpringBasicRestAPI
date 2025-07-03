package com.example.restapi.service.Impl;

import com.example.restapi.dto.CourseResponse;
import com.example.restapi.repository.CourseRepository;
import com.example.restapi.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository = new CourseRepository();

    @Override
    public List<CourseResponse> getCourses(Boolean status, String title) {
        return repository.getCourses().stream()
                .filter(c ->
                        (status == null || c.getStatus().equals(status)) &&
                        (title == null || c.getTitle().toLowerCase().contains(title.toLowerCase()))
                )
                .map(c->CourseResponse.builder()
                        .code(c.getCode())
                        .title(c.getTitle())
                        .price(c.getPrice())
                        .status(c.getStatus())
                        .build())
                .toList();
    }

    @Override
    public CourseResponse getCourseByCode(String code) {
        return repository.getCourses().stream()
                .filter(c -> c.getCode().equals(code))
                .map(c -> CourseResponse.builder()
                        .code(c.getCode())
                        .title(c.getTitle())
                        .price(c.getPrice())
                        .status(c.getStatus())
                        .build())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Not Found"));
    }

    @Override
    public CourseResponse getCourseById(String id) {
        return repository.getCourses().stream()
                .filter(c-> c.getId().equals(id))
                .map(c -> CourseResponse.builder()
                        .code(c.getCode())
                        .title(c.getTitle())
                        .price(c.getPrice())
                        .status(c.getStatus())
                        .build())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Not Found"));
    }
}
