package com.testspringboot.demo.User.Service.impl;

import com.alibaba.druid.util.StringUtils;
import com.testspringboot.demo.User.Service.RequestTokenService;
import com.testspringboot.demo.exception.IdempotentException;
import com.testspringboot.demo.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 幂等获取token实现类
 */
@Service
public class RequestTokenServiceImpl implements RequestTokenService {

    @Override
    public String createRequestToken() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        RedisUtil.getInstance().set(RedisUtil.REQUEST_TOKEN + token, token, 300);
        return token;
    }

    @Override
    public boolean deleteRequestToken(String tokenId) {
        return RedisUtil.getInstance().delHasReturn(RedisUtil.REQUEST_TOKEN + tokenId);
    }

    @Override
    public void checkRequsetToken(HttpServletRequest request) throws IdempotentException {
        String requsetToken = request.getHeader("requsetToken");
        if (StringUtils.isEmpty(requsetToken)) {
            requsetToken = request.getParameter("requsetToken");
            if (StringUtils.isEmpty(requsetToken)) {
                throw new IdempotentException("请勿多次请求");
            }
        }
        boolean flag = RedisUtil.getInstance().hasKey(RedisUtil.REQUEST_TOKEN + requsetToken);
        if (!flag) {
            throw new IdempotentException("请勿多次请求");
        }
        if (!deleteRequestToken(requsetToken)) {
            throw new IdempotentException("请勿多次请求");
        }

    }
}
