package com.spring.post.entity;

import com.spring.post.entity.ancestor.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity(name = "client")
@Table(name = "client",  schema = "public",
        uniqueConstraints = @UniqueConstraint(name = "uk_client_phone", columnNames = "phone")
)
@Getter
@Setter
public class Client extends Person {


    @OneToMany(mappedBy = "clientSender")
    private Collection<Invoice> sends;

    @OneToMany(mappedBy = "clientRecipient")
    private Collection<Invoice> recipes;


    @OneToMany
    @JoinTable(name = "client_stations",
            joinColumns = {
                    @JoinColumn(name = "client_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_client_stations_client_id"))},
            inverseJoinColumns = {
                    @JoinColumn(name = "stations_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_client_stations_station_id"))},
            uniqueConstraints = @UniqueConstraint(name = "uk_client_stations_id", columnNames = "stations_id"))
    @NotNull(message = "Client stations cannot be null")
    @Size(message = "Maximum 5 stations", min = 1, max = 5)
    private Collection<Station> stations;


    public Client(Long id, String firstname, String patronymic, String surname, String phone) {
        super(id, firstname, patronymic, surname, phone);
    }

    public Client(String firstname, String patronymic, String surname, String phone) {
        super(firstname, patronymic, surname, phone);
    }

    public Client() {
    }

    /*
     * Equals and HashCode override in Person basic class
     * */

    @Override
    public String toString() {
        return "Client{" +
                "sends=" + sends +
                ", recipes=" + recipes +
                ", stations=" + stations +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

