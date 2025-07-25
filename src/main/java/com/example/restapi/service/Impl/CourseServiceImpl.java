package com.example.restapi.service.Impl;

import com.example.restapi.domain.Course;
import com.example.restapi.dto.CourseResponse;
import com.example.restapi.dto.CreateCourseRequest;
import com.example.restapi.repository.CourseRepository;
import com.example.restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<CourseResponse> getCourses(Boolean status, String title, String code) {
        return courseRepository.getCourses().stream()
                .peek(c-> System.out.println(c.getId()))
                .filter(c ->
                        (status == null || c.getStatus().equals(status)) &&
                        (title == null || c.getTitle().toLowerCase().contains(title.toLowerCase())) &&
                        (code == null || c.getCode().equalsIgnoreCase(code))
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
    public CourseResponse getCourseById(String id) {
        return courseRepository.getCourses().stream()
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

    @Override
    public CourseResponse createCourse(CreateCourseRequest createCourseRequest) {

        boolean isCourseCodeExisted = courseRepository.getCourses()
                .stream()
                .anyMatch(c->c.getCode().equals(createCourseRequest.code()));

        if(isCourseCodeExisted){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Course code already exists");
        }


        Course course = Course.builder()
                .id(UUID.randomUUID().toString())
                .code(createCourseRequest.code())
                .title(createCourseRequest.title())
                .price(createCourseRequest.price())
                .status(false)
                .build();

        courseRepository.getCourses().add(course);

        return CourseResponse.builder()
                .code(course.getCode())
                .title(course.getTitle())
                .price(course.getPrice())
                .status(false)
                .build();
    }

    @Override
    public void deleteCourseByCode(String code) {
        boolean removed = courseRepository.getCourses()
                .removeIf(c -> c.getCode().equalsIgnoreCase(code));

        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with code " + code + " not found");
        }
    }

}
