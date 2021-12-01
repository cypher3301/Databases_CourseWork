package com.spring.post.service;

import com.spring.post.entity.Users;

public interface AuthenticateService {

    boolean authorize(Users user);

}
