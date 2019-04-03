package com.anabol.movieland.web.auth;

import com.anabol.movieland.entity.User;

public class UserHolder {
    static private ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setCurrentUser(User user) {
        threadLocal.set(user);
    }

    public static User getCurrentUser() {
        return threadLocal.get();
    }
}