package com.anabol.movieland.web.auth.annotation;

import com.anabol.movieland.entity.UserRole;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
    UserRole value() default UserRole.USER;
}
