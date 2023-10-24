package com.acme.learning.platform.profiles.domain.model.commands;

public record CreateProfileCommand(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipCode) {
}
