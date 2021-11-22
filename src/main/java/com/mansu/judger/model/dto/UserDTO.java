package com.mansu.judger.model.dto;

import java.sql.Timestamp;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private Timestamp created_at;

    public UserDTO(int id, String username, String password, String email, Timestamp created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
    }

    public UserDTO(int id, String username, String email, Timestamp created_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created_at = created_at;
    }

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }
}
