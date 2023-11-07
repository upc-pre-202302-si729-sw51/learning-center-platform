package com.acme.learning.platform.iam.domain.model.entities;


import com.acme.learning.platform.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

/**
 * Role
 * Entity with default Roles
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    /**
     * Role Constructor
     *
     * @param name Role name
     */
    public Role(Roles name) {
        this.name = name;
    }
}
