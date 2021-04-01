package com.sunset.grass.entity;

public class User {
    private String id;
    private String name;
    private String password;
    private String lastPassword;
    private String description;
    private String created_date;

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
