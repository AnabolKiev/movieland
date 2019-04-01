package com.anabol.movieland.web.auth.interceptor;

import com.anabol.movieland.entity.UserRole;
import com.anabol.movieland.service.SecurityService;
import com.anabol.movieland.web.auth.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class UserRoleInterceptor implements HandlerInterceptor {
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        if (request.getHeader("uuid") != null) {
            Optional<Session> sessionOptional = securityService.getByToken(request.getHeader("uuid"));
            if (sessionOptional.isPresent() && sessionOptional.get().getUser().getRole() == UserRole.USER) {
                request.setAttribute("user", sessionOptional.get().getUser());
                log.info("User authorized successfully to post review");
                return true;
            }
        }
        log.warn("Unauthorized attempt to post review");
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
