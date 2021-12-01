package com.spring.post.entity.nullObjects;

import com.spring.post.entity.Operator;

public class NullOperator extends Operator {
    @Override
    public Long getId() {
        return null;
    }
}
