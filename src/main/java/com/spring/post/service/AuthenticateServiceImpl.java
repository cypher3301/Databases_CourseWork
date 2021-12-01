package com.spring.post.service;

import com.spring.post.entity.Users;
import com.spring.post.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UsersRepository usersRepository;

    @Autowired
    public AuthenticateServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean authorize(Users user) {
        return usersRepository.existsByUsername(user.getUsername()) &&
                usersRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
