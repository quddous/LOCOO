package com.trial_1.locoo.models;

public class users {
    public users(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public users(String s, String toString){ }

    String  email;

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

    public String getName() {
        return name;
    }

    public void setId(String name) {
        this.name = name;
    }

    String password;
    String name;
}
