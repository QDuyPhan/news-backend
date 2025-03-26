package com.quangduy.newsbackend.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.quangduy.newsbackend.dto.request.AuthenticationRequest;
import com.quangduy.newsbackend.dto.request.IntrospectRequest;
import com.quangduy.newsbackend.dto.request.LogoutRequest;
import com.quangduy.newsbackend.dto.request.RefreshRequest;
import com.quangduy.newsbackend.dto.response.ApiResponse;
import com.quangduy.newsbackend.dto.response.AuthenticationResponse;
import com.quangduy.newsbackend.dto.response.IntrospectResponse;
import com.quangduy.newsbackend.dto.response.RefreshResponse;
import com.quangduy.newsbackend.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
            throws JOSEException {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<RefreshResponse> refresh(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        return ApiResponse.<RefreshResponse>builder()
                .result(authenticationService.refreshToken(request))
                .build();
    }
}
