package com.mpc.vauth.model;

import java.io.Serializable;
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
    private String username;
    private Object detailUser;

    public JwtResponse() {
    }
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getDetailUser() {
        return detailUser;
    }

    public void setDetailUser(Object detailUser) {
        this.detailUser = detailUser;
    }
}
