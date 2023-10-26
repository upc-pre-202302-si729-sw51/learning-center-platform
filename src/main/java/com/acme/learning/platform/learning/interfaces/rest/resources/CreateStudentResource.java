package com.acme.learning.platform.learning.interfaces.rest.resources;

public record CreateStudentResource(String firstName, String lastName, String email, String address, String number, String city, String zipCode, String country ) {
}
