package com.poolplatform.features.auth.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect credentials")
public class AuthenticationException extends RuntimeException {
}
