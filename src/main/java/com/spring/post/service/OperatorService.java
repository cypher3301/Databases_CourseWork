package com.spring.post.service;

import com.spring.post.entity.Operator;

import java.util.List;

public interface OperatorService {

    List<Operator> findAll();

    Operator findByLogin(String login);
}

