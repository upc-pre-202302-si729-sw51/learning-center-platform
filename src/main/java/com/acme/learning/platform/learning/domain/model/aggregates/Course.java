package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.learning.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Course extends AuditableModel {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Embedded
    @Getter
    private LearningPath learningPath;

    public Course() {
    }
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }
}
