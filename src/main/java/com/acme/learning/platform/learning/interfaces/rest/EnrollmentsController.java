package com.acme.learning.platform.learning.interfaces.rest;

import com.acme.learning.platform.learning.domain.model.commands.ConfirmEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.queries.GetAllEnrollmentsQuery;
import com.acme.learning.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.acme.learning.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.learning.platform.learning.domain.services.EnrollmentQueryService;
import com.acme.learning.platform.learning.interfaces.rest.resources.EnrollmentResource;
import com.acme.learning.platform.learning.interfaces.rest.resources.RequestEnrollmentResource;
import com.acme.learning.platform.learning.interfaces.rest.transform.EnrollmentResourceFromEntityAssembler;
import com.acme.learning.platform.learning.interfaces.rest.transform.RequestEnrollmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * EnrollmentsController
 * <p>
 *     This class is the entry point for all the enrollment related operations.
 *     It exposes the endpoints for the enrollment management.
 *     The endpoints are:
 *     <ul>
 *         <li>POST /api/v1/enrollments</li>
 *         <li>POST /api/v1/enrollments/{enrollmentId}/confirm</li>
 *         <li>POST /api/v1/enrollments/{enrollmentId}/reject</li>
 *         <li>POST /api/v1/enrollments/{enrollmentId}/cancel</li>
 *         <li>GET /api/v1/enrollments</li>
 *     </ul>
 *  </p>
 */
@RestController
@RequestMapping(value = "/api/v1/enrollments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Enrollments", description = "Enrollment Management Endpoints")
public class EnrollmentsController {
    private final EnrollmentCommandService enrollmentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    public EnrollmentsController(EnrollmentCommandService enrollmentCommandService, EnrollmentQueryService enrollmentQueryService) {
        this.enrollmentCommandService = enrollmentCommandService;
        this.enrollmentQueryService = enrollmentQueryService;
    }

    /**
     * Requests the enrollment of a student to a course.
     * @param resource The request enrollment resource containing the student record identifier and the course identifier.
     * @return The enrollment resource.
     * @see RequestEnrollmentResource
     * @see EnrollmentResource
     */
    @PostMapping
    public ResponseEntity<EnrollmentResource> requestEnrollment(@RequestBody RequestEnrollmentResource resource) {
        var requestEnrollmentCommand = RequestEnrollmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var enrollmentId = enrollmentCommandService.handle(requestEnrollmentCommand);
        if (enrollmentId == 0L) return ResponseEntity.badRequest().build();

        var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(enrollmentId);
        var enrollment = enrollmentQueryService.handle(getEnrollmentByIdQuery);
        if (enrollment.isEmpty()) return ResponseEntity.notFound().build();
        var enrollmentResource = EnrollmentResourceFromEntityAssembler.toResourceFromEntity(enrollment.get());
        return new ResponseEntity<>(enrollmentResource, HttpStatus.CREATED);
    }

    /**
     * Confirms the enrollment with the given id.
     * @param enrollmentId The enrollment id.
     * @return The enrollment id.
     */
    @PostMapping("/{enrollmentId}/confirmations")
    public ResponseEntity<?> confirmEnrollment(@PathVariable Long enrollmentId) {
        var confirmEnrollmentCommand = new ConfirmEnrollmentCommand(enrollmentId);
        var confirmedEnrollmentId = enrollmentCommandService.handle(confirmEnrollmentCommand);
        return ResponseEntity.ok(confirmedEnrollmentId);
    }

    /**
     * Rejects the enrollment with the given id.
     * @param enrollmentId The enrollment id.
     * @return The enrollment id.
     */
    @PostMapping("/{enrollmentId}/rejections")
    public ResponseEntity<?> rejectEnrollment(@PathVariable Long enrollmentId) {
        var rejectEnrollmentCommand = new ConfirmEnrollmentCommand(enrollmentId);
        var rejectedEnrollmentId = enrollmentCommandService.handle(rejectEnrollmentCommand);
        return ResponseEntity.ok(rejectedEnrollmentId);
    }

    /**
     * Cancels the enrollment with the given id.
     * @param enrollmentId The enrollment id.
     * @return The enrollment id.
     */
    @PostMapping("/{enrollmentId}/cancellations")
    public ResponseEntity<?> cancelEnrollment(@PathVariable Long enrollmentId) {
        var cancelEnrollmentCommand = new ConfirmEnrollmentCommand(enrollmentId);
        var canceledEnrollmentId = enrollmentCommandService.handle(cancelEnrollmentCommand);
        return ResponseEntity.ok(canceledEnrollmentId);
    }

    /**
     * Get All Enrollments available
     * @return The list of enrollments.
     * @see EnrollmentResource
     */
    @GetMapping
    public ResponseEntity<List<EnrollmentResource>> getAllEnrollments() {
        var getAllEnrollmentsQuery = new GetAllEnrollmentsQuery();
        var enrollments = enrollmentQueryService.handle(getAllEnrollmentsQuery);
        var enrollmentResources = enrollments.stream().map(EnrollmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(enrollmentResources);
    }

    /**
     * Get Enrollment by Identifier.
     * @param enrollmentId The enrollment id.
     * @return The enrollment resource.
     * @see EnrollmentResource
     */
    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResource> getEnrollmentById(@PathVariable Long enrollmentId) {
        var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(enrollmentId);
        var enrollment = enrollmentQueryService.handle(getEnrollmentByIdQuery);
        if (enrollment.isEmpty()) return ResponseEntity.notFound().build();
        var enrollmentResource = EnrollmentResourceFromEntityAssembler.toResourceFromEntity(enrollment.get());
        return ResponseEntity.ok(enrollmentResource);
    }
}
