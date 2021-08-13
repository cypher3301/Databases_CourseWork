package com.example.demo.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.example.demo.dao.entity.ancestor.Util.regExpEmail;


@Entity(name = "clientAccount")
@Table(name = "client_account", catalog = "postOffice", schema = "public",
        uniqueConstraints = @UniqueConstraint(name = "uk_client_account_email", columnNames = "email")
)
@NoArgsConstructor
@Getter
@Setter
public class ClientAccount {

    @Id
    @NaturalId
    @Column(name = "email", nullable = false)
    @NotBlank(      message = "Employee email is illegal or empty")
    @Min(value = 5, message = "Email is too less")
    @Email(         message = "Email is not valid",
            regexp = regExpEmail)
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Client account password cannot be empty")
    private byte[] password;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "account_client"))
    @NotNull(message = "Account cannot have client is null")
    private Client client;


    public ClientAccount(String email,
                         @NotNull(message = "Client account password cannot be empty") byte[] password,
                         Client client) {
        this.email = email;
        this.password = password;
        this.client = client;
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
