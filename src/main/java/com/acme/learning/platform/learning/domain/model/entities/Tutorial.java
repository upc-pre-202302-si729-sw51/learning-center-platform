package com.acme.learning.platform.learning.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Tutorial {
    @Id
    @Getter
    private Long id;

    private String title;
    private String description;

    private String contentUrl;



}
