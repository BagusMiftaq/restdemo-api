package com.enigma.service;

import com.enigma.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> list() throws Exception;

    Course create(Course course) throws Exception;

    Course get(String id) throws Exception;

    void update(Course course, String id);

    void delete(String id);

    Optional<List<Course>> getBy(String key, String value) throws Exception;
}
