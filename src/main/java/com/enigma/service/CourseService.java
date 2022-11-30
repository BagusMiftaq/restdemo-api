package com.enigma.service;

import com.enigma.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> list();

    Course create(Course course);

    Optional<Course> get(String id);

    void update(Course course, String id);

    void delete(String id);

    List<Course> getBy(String key, String value);
}
