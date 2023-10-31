package com.acme.learning.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StreetAddress(
        String street,
        String number,
        String city,
        String zipCode,
        String country
) {
    public StreetAddress() {
        this(null, null, null, null, null);
    }

    public StreetAddress(String street, String city, String zipCode, String country) {
        this(street, null, city, zipCode, country);
    }

    public String getStreetAddress() {
        return String.format("%s %sth, %s, %s, %s", street, number, city, zipCode, country);
    }

    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }

        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or blank");
        }

        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }

    }
}
