package com.example.demo.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ClientAccount {

    @Id
    private long id;


    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private byte[] password;

    @OneToOne
    @JoinColumn(name = "client_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "account_client"))
    private Client client;

    public ClientAccount(String email, @NotNull byte[] password, Client client) {
        this.email = email;
        this.password = password;
        this.client = client;
        this.id=client.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAccount)) return false;

        ClientAccount that = (ClientAccount) o;

        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        return getClient() != null ? getClient().equals(that.getClient()) : that.getClient() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        return result;
    }
}
