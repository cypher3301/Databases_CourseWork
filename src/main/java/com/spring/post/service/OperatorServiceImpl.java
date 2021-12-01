package com.spring.post.service;

import com.spring.post.entity.Operator;
import com.spring.post.entity.nullObjects.NullOperator;
import com.spring.post.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {


    private final OperatorRepository operatorRepository;

    @Autowired
    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public List<Operator> findAll() {
        return operatorRepository.findAll();
    }

    @Override
    public Operator findByLogin(String login) {
        if (operatorRepository.existsOperatorByLogin(login))
            return operatorRepository.findOperatorByLogin(login);
        else return new NullOperator();
    }
}
