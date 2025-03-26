package com.quangduy.newsbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(409, "User existed, please again", HttpStatus.CONFLICT),
    NOT_FOUND(404, "Not Found", HttpStatus.NOT_FOUND),
    INVALID_KEY(1002, "Invalid message key", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(400, "Username must be at least 3 character", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(400, "Password must be at least 8 character", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(400, "Malformed token: Token is missing or malformed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXITSTED(404, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "Access Deny: You do not permission", HttpStatus.FORBIDDEN),
    DATABASE_ERROR(1001, "Database error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatusCode statusCode;
}
