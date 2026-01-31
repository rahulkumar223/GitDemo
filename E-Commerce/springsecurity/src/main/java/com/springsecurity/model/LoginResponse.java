package com.springsecurity.model;

import java.util.List;

public class LoginResponse {

    private String username;
    private String jwt;
    private List<String> roles;

    public LoginResponse() {
    }

    public LoginResponse(String username, String jwt, List<String> roles) {
        this.username = username;
        this.jwt = jwt;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
