package com.anabol.movieland.config;

import com.anabol.movieland.service.SecurityService;
import com.anabol.movieland.web.auth.interceptor.AuthInterceptor;
import com.anabol.movieland.web.auth.interceptor.LogInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.anabol.movieland.web")
public class ServletConfig implements WebMvcConfigurer {

    private final SecurityService securityService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LogInterceptor logInterceptor = new LogInterceptor();
        logInterceptor.setSecurityService(securityService);
        registry.addInterceptor(logInterceptor);

        AuthInterceptor authInterceptor = new AuthInterceptor();
        authInterceptor.setSecurityService(securityService);
        registry.addInterceptor(authInterceptor);
    }
}
