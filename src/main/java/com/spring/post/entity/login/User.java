package com.spring.post.entity.login;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private String login;
    private String password;
    private boolean enabled;
}
