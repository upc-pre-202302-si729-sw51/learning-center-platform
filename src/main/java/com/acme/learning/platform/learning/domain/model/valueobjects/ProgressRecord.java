package com.acme.learning.platform.learning.domain.model.valueobjects;

import com.acme.learning.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {
    @OneToMany(mappedBy = "enrollment")
    private List<ProgressRecordItem> progressRecordItems;


    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }


}
