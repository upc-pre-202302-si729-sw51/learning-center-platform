package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.learning.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

/**
 * Represents a course.
 * The course is an aggregate root
 */
@Getter
@Entity
public class Course extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    /**
     * The learning path for this course
     */
    @Embedded
    private final LearningPath learningPath;

    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.learningPath = new LearningPath();
    }
    public Course(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    /**
     * Updates the information for this course
     * @param title the new title
     * @param description the new description
     * @return the updated course
     */
    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }
}
