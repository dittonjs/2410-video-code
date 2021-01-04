package com.example.contacts;

public class Contact {
    private String personName;
    private String phoneNumber;
    private String email;

    public Contact(String personName, String phoneNumber, String email) {
        this.personName = personName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
