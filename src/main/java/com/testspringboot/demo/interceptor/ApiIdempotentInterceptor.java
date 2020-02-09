package com.testspringboot.demo.interceptor;

import com.testspringboot.demo.User.Service.RequestTokenService;
import com.testspringboot.demo.comment.ApiIdempotent;
import com.testspringboot.demo.exception.IdempotentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.logging.Handler;

/**
 * API幂等拦截器
 */
public class ApiIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestTokenService requestTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("-------幂等拦截器preHandle--------");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent apiIdempotent = method.getAnnotation(ApiIdempotent.class);
        if (apiIdempotent != null) {
            requestTokenService.checkRequsetToken(request);
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("-------幂等拦截器postHandle--------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("-------幂等拦截器afterCompletion--------");
    }
}
