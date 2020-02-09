package com.testspringboot.demo.exceptionHandler.controller;

import com.testspringboot.demo.config.ResultData;
import com.testspringboot.demo.exception.IdempotentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理捕获类
 */
@RestControllerAdvice
public class IdempotentExceptionHandlerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {IdempotentException.class})
    public ResultData handlerExecptionForIdempotent(IdempotentException e) {
        logger.error("11111");
        //System.out.println("----IdempotentException---");
        return new ResultData(500, e.getMessage());
    }
}
