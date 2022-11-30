package com.enigma.repository;

import com.enigma.model.Course;
import com.enigma.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CourseRepositoryImpl implements CourseRepository{
    @Autowired
    RandomStringGenerator randomStringGenerator;

    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course: courses
             ) {
            if (course.getCourseId().equals(id)){
                return Optional.of(course);
            }
        }

        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course existingCourse : courses
             ) {
            if (existingCourse.getCourseId().equals(id)){
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }

        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course course: courses
             ) {
            if (course.getCourseId().equals(id)){
                courses.remove(course);
            }
            
        }
    }

    @Override
    public Optional<Course> findByTitle(String title) throws Exception {
        for (Course course: courses
        ) {
            if (course.getTitle().equals(title)){
                return Optional.of(course);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Course> findByDesc(String description) throws Exception {
        for (Course course: courses
        ) {
            if (course.getDescription().equals(description)){
                return Optional.of(course);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Course> findByLink(String link) throws Exception {
        for (Course course: courses
        ) {
            if (course.getLink().equals(link)){
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }
}
