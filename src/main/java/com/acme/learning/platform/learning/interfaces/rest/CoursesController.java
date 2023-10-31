package com.acme.learning.platform.learning.interfaces.rest;

import com.acme.learning.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.learning.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learning.platform.learning.domain.model.queries.GetAllCoursesQuery;
import com.acme.learning.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.learning.platform.learning.domain.services.CourseCommandService;
import com.acme.learning.platform.learning.domain.services.CourseQueryService;
import com.acme.learning.platform.learning.interfaces.rest.resources.CourseResource;
import com.acme.learning.platform.learning.interfaces.rest.resources.CreateCourseResource;
import com.acme.learning.platform.learning.interfaces.rest.resources.UpdateCourseResource;
import com.acme.learning.platform.learning.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import com.acme.learning.platform.learning.interfaces.rest.transform.UpdateCourseCommandFromResourceAssembler;
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
    public ResponseEntity<CourseResource> createCourse(@RequestBody CreateCourseResource createCourseResource) {
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

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResource> updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseResource updateCourseResource) {
        var updateCourseCommand = UpdateCourseCommandFromResourceAssembler.toCommandFromResource(courseId, updateCourseResource);
        var updatedCourse = courseCommandService.handle(updateCourseCommand);
        if (updatedCourse.isEmpty()) {
            System.out.println("updatedCourseId: " + updatedCourse.get().getId());
            return ResponseEntity.badRequest().build();
        }
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(updatedCourse.get());
        return ResponseEntity.ok(courseResource);
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        var deleteCourseCommand = new DeleteCourseCommand(courseId);
        courseCommandService.handle(deleteCourseCommand);
        return ResponseEntity.ok("Course with given id successfully deleted");
    }
}