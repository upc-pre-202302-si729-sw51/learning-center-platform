package com.acme.learning.platform.learning.domain.model.entities;

import com.acme.learning.platform.learning.domain.model.valueobjects.ProgressStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Date;

@Entity
public class ProgressRecordItem {
    @Id
    private Long id;

    @Getter
    private Long enrollmentId;

    @Getter
    private Long tutorialId;

    private ProgressStatus status;

    private Date completedAt;

    public ProgressRecordItem(Long enrollmentId, Long tutorialId) {
        this.enrollmentId = enrollmentId;
        this.tutorialId = tutorialId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public ProgressRecordItem() {

    }

    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }




}
