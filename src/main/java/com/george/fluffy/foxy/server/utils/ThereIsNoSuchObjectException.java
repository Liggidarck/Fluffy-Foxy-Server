package com.george.fluffy.foxy.server.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Object not found")
public class ThereIsNoSuchObjectException extends RuntimeException {}