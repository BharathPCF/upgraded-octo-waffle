package com.bharath.security;

import java.io.Serializable;


public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String userName;

    public AuthenticationResponse(String userName) {
        this.userName = userName;
    }

	public String getUserName() {
		return userName;
	}

    
}
