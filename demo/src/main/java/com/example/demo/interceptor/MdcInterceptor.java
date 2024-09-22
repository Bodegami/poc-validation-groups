package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Component
@Order(1)
public class MdcInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_ID = "x-correlationId";
    private static final Logger log = LoggerFactory.getLogger(MdcInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().contains("prometheus") || request.getRequestURI().contains("metrics")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        var correlationId = request.getHeader(CORRELATION_ID);

        if (correlationId == null || correlationId.isBlank()) {
            MDC.put(CORRELATION_ID, UUID.randomUUID().toString());
        }
        else {
            MDC.put(CORRELATION_ID, correlationId);
        }

        log.info("Starting Request");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String result;

        if (response.getStatus() >= 400 || response.getStatus() < 200) {
            result = "Unsuccessful request!";
        }
        else {
            result = "Successful request!";
        }

        log.info(String.format("[%d] - %s", response.getStatus(), result));

        MDC.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
