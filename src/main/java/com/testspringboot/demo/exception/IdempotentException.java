package com.testspringboot.demo.exception;

/**
 * 幂等异常
 */
public class IdempotentException extends RuntimeException {
    public IdempotentException(String message) {
        super(message);
    }
}
