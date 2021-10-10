package com.web.curation.model.user;

import lombok.Getter;


@Getter
public class AuthenticationResponse {
    private String accessionToken;
    private String refreshToken;

    public AuthenticationResponse(String accessToken, String refreshToken) {
        this.accessionToken = accessToken;
        this.refreshToken = refreshToken;
    }
}