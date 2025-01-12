package fr.pantheonsorbonne.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String email;
    private LocalDate licenseDate;

    // Constructors
    public User() {}

    public User(Long id, String name, String email, LocalDate licenseDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.licenseDate = licenseDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(LocalDate licenseDate) {
        this.licenseDate = licenseDate;
    }
}
