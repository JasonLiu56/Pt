package com.jxcia.pt.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenException extends AuthenticationException {

    public JwtTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtTokenException(String msg) {
        super(msg);
    }

}
