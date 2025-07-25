package com.example.restapi.service;

import com.example.restapi.dto.CourseResponse;
import com.example.restapi.dto.CreateCourseRequest;

import java.util.List;

public interface CourseService {
    /**
     * Get All Course Data
     * @author Porkeat
     * @return List<CourseResponse>
     */
    List<CourseResponse> getCourses(Boolean status,String title,String code);
    /**
     * Get Course By id But That id is UUID
     * @author Porkeat
     * @return CourseResponse
     */
    CourseResponse getCourseById(String id);
    CourseResponse createCourse(CreateCourseRequest createCourseRequest);
    void deleteCourseByCode(String code);
}
