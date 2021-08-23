package com.example.demo.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Arrays;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Authentication {

    protected String login;

    private byte[] password;


    public Authentication(String login, byte[] password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authentication)) return false;

        Authentication that = (Authentication) o;

        if (getLogin() != null ? !getLogin().equals(that.getLogin()) : that.getLogin() != null) return false;
        return Arrays.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + Arrays.hashCode(getPassword());
        return result;
    }
}
