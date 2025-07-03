package com.example.restapi.service;

import com.example.restapi.dto.CourseResponse;

import java.util.List;

public interface CourseService {
    /**
     * Get All Course Data
     * @author Porkeat
     * @return List<CourseResponse>
     */
    List<CourseResponse> getCourses(Boolean status,String title);
    /**
     * Get Course By Code
     * @author Porkeat
     * @return CourseResponse
     */
    CourseResponse getCourseByCode(String code);
    /**
     * Get Course By id But That id is UUID
     * @author Porkeat
     * @return CourseResponse
     */
    CourseResponse getCourseById(String id);
}
