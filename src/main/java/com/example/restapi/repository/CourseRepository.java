package com.example.restapi.repository;

import com.example.restapi.domain.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Getter
@Setter
public class CourseRepository {
    private List<Course> courses;

    public CourseRepository(){
        courses = new ArrayList<>();
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-001")
                .title("Spring Framework")
                .price(100.0)
                .status(true)
                .build());
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-002")
                .title("Next Js")
                .price(80.0)
                .status(false)
                .build());
    }

}
