package com.anabol.movieland.web.auth.interceptor;

import com.anabol.movieland.entity.User;
import com.anabol.movieland.entity.UserRole;
import com.anabol.movieland.service.SecurityService;
import com.anabol.movieland.web.auth.UserHolder;
import com.anabol.movieland.web.auth.annotation.Secured;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        boolean isAuthorized = false;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = HandlerMethod.class.cast(handler);

            if (handlerMethod.hasMethodAnnotation(Secured.class)) {
                List<UserRole> expectedRoles = Arrays.asList(handlerMethod.getMethodAnnotation(Secured.class).value());
                Optional<User> userOptional = securityService.validateByTokenAndRole(request.getHeader("uuid"), expectedRoles);
                if (userOptional.isPresent()) {
                    UserHolder.setCurrentUser(userOptional.get());
                    isAuthorized = true;
                }
            } else {
                isAuthorized = true;
            }
        }

        if (isAuthorized) {
            log.info("User authorized successfully to post review");
            return true;
        }
        log.warn("Unauthorized attempt to post review");
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserHolder.clean();
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
