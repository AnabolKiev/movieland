package com.anabol.movieland.web.auth;

import com.anabol.movieland.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private static final String GUEST_USER = "guest";
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        String user = GUEST_USER;
        if (request.getHeader("uuid") != null) {
            Optional<Session> sessionOptional = securityService.getByToken(request.getHeader("uuid"));
            user = (sessionOptional.isPresent()) ? sessionOptional.get().getUser().getEmail() : GUEST_USER;
        }
        MDC.put("user", user);
        return true;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
