package com.quangduy.newsbackend.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import com.quangduy.newsbackend.dto.request.LogoutRequest;
import com.quangduy.newsbackend.dto.request.RefreshRequest;
import com.quangduy.newsbackend.dto.response.RefreshResponse;
import com.quangduy.newsbackend.entity.InvalidatedToken;
import com.quangduy.newsbackend.repository.InvalidatedTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.quangduy.newsbackend.dto.request.AuthenticationRequest;
import com.quangduy.newsbackend.dto.request.IntrospectRequest;
import com.quangduy.newsbackend.dto.response.AuthenticationResponse;
import com.quangduy.newsbackend.dto.response.IntrospectResponse;
import com.quangduy.newsbackend.entity.User;
import com.quangduy.newsbackend.exception.AppException;
import com.quangduy.newsbackend.exception.ErrorCode;
import com.quangduy.newsbackend.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException {
        var user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITSTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);

        return AuthenticationResponse.builder().authenticated(true).token(token).build();
    }

    // generate token
    private String generateToken(User user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("quangduy.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

        return jwsObject.serialize();
    }

    // verified token
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean inValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            inValid = false;
        }
        return IntrospectResponse.builder()
                .valid(inValid)
                .build();
    }

    // tạo scope chứa các thông tin role của user
    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> stringJoiner.add(role.getName()));
        return stringJoiner.toString();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken(), true);
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expirationDate = signToken.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expirationDate)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationDate =
                (isRefresh) ? new Date(signedJWT.getJWTClaimsSet()
                        .getIssueTime().toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                        : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verifiedToken = signedJWT.verify(verifier);
        // nếu chữ ký không hợp lệ hoặc token hết hạn
        if (!verifiedToken && expirationDate.after(new Date())) throw new AppException(ErrorCode.UNAUTHENTICATED);
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        return signedJWT;
    }

    public RefreshResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken(), true);
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expirationDate = signToken.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expirationDate)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);

        var username = signToken.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITSTED));
        var token = generateToken(user);
        return RefreshResponse.builder().authenticated(true).token(token).build();
    }
}
