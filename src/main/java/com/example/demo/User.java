package com.example.demo;


public class User {
    private String username;
    private String email;
    private String password;
    private boolean isAdmin; // Customer or Admin

    // Constructor
    public User( String username, String email, String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setName(String name) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin (boolean admin) {
        isAdmin = admin;
    }

}
