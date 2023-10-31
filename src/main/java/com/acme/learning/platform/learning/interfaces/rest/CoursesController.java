package com.acme.learning.platform.learning.interfaces.rest;

import com.acme.learning.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.learning.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.learning.platform.learning.domain.services.CourseCommandService;
import com.acme.learning.platform.learning.domain.services.CourseQueryService;
import com.acme.learning.platform.learning.interfaces.rest.resources.CourseResource;
import com.acme.learning.platform.learning.interfaces.rest.resources.CreateCourseResource;
import com.acme.learning.platform.learning.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {
    private final CourseCommandService courseCommandService;
    private final CourseQueryService courseQueryService;

    public CoursesController(CourseCommandService courseCommandService, CourseQueryService courseQueryService) {
        this.courseCommandService = courseCommandService;
        this.courseQueryService = courseQueryService;
    }

    @PostMapping
    public ResponseEntity<CourseResource> createCourse(CreateCourseResource createCourseResource) {
        var createCourseCommand = new CreateCourseCommand(createCourseResource.title(), createCourseResource.description());
        var courseId = courseCommandService.handle(createCourseCommand);
        if (courseId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) return ResponseEntity.badRequest().build();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(course.get());
        return new ResponseEntity<>(courseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResource> getCourseById(@PathVariable Long courseId) {
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) return ResponseEntity.badRequest().build();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(course.get());
        return ResponseEntity.ok(courseResource);
    }

    @GetMapping
    public ResponseEntity<List<CourseResource>> getAllCourses() {
        var getAllCoursesQuery = new GetAllCoursesQuery();
        var courses = courseQueryService.handle(getAllCoursesQuery);
        var courseResources = courses.stream().map(CourseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(courseResources);
    }
}
