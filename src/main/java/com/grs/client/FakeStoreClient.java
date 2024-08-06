package com.grs.client;

import com.grs.model.dto.HostDto;
import com.grs.model.dto.LoginRequestDto;
import com.grs.model.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthClient {

    private final RestClient restClient;

    public AuthClient(@Value("${auth.base.url}") String url) {
        restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    public HostDto callAuthSignup(HostDto request) {
        return restClient.post()
                .uri("/signup")
                .body(request)
                .retrieve()
                .body(HostDto.class);
    }

    public LoginResponseDto callAuthLogin(LoginRequestDto request) {
        return restClient.post()
                .uri("/login")
                .body(request)
                .retrieve()
                .body(LoginResponseDto.class);
    }
}
