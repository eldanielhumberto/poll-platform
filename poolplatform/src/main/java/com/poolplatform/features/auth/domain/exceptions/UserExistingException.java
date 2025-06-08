package com.poolplatform.features.auth.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The account already exists")
public class UserExistingException extends RuntimeException {
}
