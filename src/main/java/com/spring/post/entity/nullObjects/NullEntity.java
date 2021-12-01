package com.spring.post.entity.nullObjects;

import com.spring.post.entity.ancestor.BaseEntity;

public class NullEntity implements BaseEntity {
    @Override
    public Long getId() {
        return null;
    }
}
