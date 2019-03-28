package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.UserDao;
import com.anabol.movieland.entity.User;
import com.anabol.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserDao userDao;

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
