package com.enigma.service;

import com.enigma.model.Course;
import com.enigma.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> list() {
        try {
            return courseRepository.getAll();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            return courseRepository.create(course);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> getBy(String key, String value) {
        List<Course> courses = new ArrayList<>();
        try {
               switch (key){
                case "title":
                    return courses = courseRepository.getAll().stream().filter(course -> course.getTitle().equals(value)).collect(Collectors.toList());
                case "description":
                    return courses = courseRepository.getAll().stream().filter(course -> course.getDescription().equals(value)).collect(Collectors.toList());
                case "link":
                    return courses = courseRepository.getAll().stream().filter(course -> course.getLink().equals(value)).collect(Collectors.toList());
                default:
                    return courses = courseRepository.findById(value).stream().collect(Collectors.toList());
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
