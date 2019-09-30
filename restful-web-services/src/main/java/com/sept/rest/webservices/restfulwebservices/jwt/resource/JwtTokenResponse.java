package com.sept.rest.webservices.restfulwebservices.jwt.resource;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;

  private final String token;
  private final Long id;

    public JwtTokenResponse(String token, Long id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }
    
    public Long getId() {
        return this.id;
    }
}