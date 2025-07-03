package com.example.restapi.controller;
import com.example.restapi.dto.CourseResponse;
import com.example.restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller + ResponseBody
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getCourses(
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) String title) {
        return courseService.getCourses(status, title);
    }

    @GetMapping("/code/{code}")
    public CourseResponse getCourseByCode(@PathVariable String code){
        return courseService.getCourseByCode(code);
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable String id){
        return courseService.getCourseById(id);
    }

}
