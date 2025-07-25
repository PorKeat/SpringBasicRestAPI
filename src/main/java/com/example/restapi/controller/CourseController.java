package com.example.restapi.controller;
import com.example.restapi.dto.CourseResponse;
import com.example.restapi.dto.CreateCourseRequest;
import com.example.restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String code) {
        return courseService.getCourses(status, title, code);
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable String id){
        return courseService.getCourseById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseResponse createCourse(@RequestBody CreateCourseRequest createCourseRequest){
        return courseService.createCourse(createCourseRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteCourseByCode(@PathVariable String code) {
        courseService.deleteCourseByCode(code);
    }


}
