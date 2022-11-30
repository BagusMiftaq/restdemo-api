package com.enigma.repository;

import com.enigma.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    List<Course> getAll() throws Exception;

    Course create(Course course) throws Exception;

    Optional<Course> findById(String id) throws Exception;

    void update(Course course, String id) throws Exception;

    void delete(String id) throws Exception;

    Optional<Course> findByTitle(String title) throws Exception;

    Optional<Course> findByDesc(String description) throws Exception;

    Optional<Course> findByLink(String link) throws Exception;
}
