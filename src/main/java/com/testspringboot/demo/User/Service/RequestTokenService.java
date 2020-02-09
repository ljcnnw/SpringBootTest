package com.testspringboot.demo.User.Service;

import com.testspringboot.demo.exception.IdempotentException;

import javax.servlet.http.HttpServletRequest;

/**
 * 幂等获取token接口
 */
public interface RequestTokenService {
    String createRequestToken();

    boolean deleteRequestToken(String tokenId);

    void checkRequsetToken(HttpServletRequest request) throws IdempotentException;
}
