package com.george.fluffy.foxy.server.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoxyError {

    private int statusCode;
    private String message;

    public FoxyError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
