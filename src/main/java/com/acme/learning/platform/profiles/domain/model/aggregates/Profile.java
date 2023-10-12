package com.acme.learning.platform.profiles.domain.model.aggregates;

import com.acme.learning.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.acme.learning.platform.profiles.domain.model.valueobjects.PersonName;
import com.acme.learning.platform.profiles.domain.model.valueobjects.StreetAddress;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

@Entity
public class Profile extends AbstractAggregateRoot<Profile> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    private StreetAddress address;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Profile(String firstName, String lastName, String email, String street, String number, String city, String zipCode, String country) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.address = new StreetAddress(street, number, city, zipCode, country);
    }

    public Profile() {

    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public String getFullName() {
        return this.name.getFullName();
    }

    public String getStreetAddress() {
        return this.address.getStreetAddress();
    }



}
