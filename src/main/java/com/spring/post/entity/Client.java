package com.spring.post.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Builder
@NoArgsConstructor
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "firstname", nullable = false, length = 24)
    private String firstname;
    @Basic
    @Column(name = "patronymic", nullable = false, length = 24)
    private String patronymic;
    @Basic
    @Column(name = "phone", nullable = false, length = 24)
    private String phone;
    @Basic
    @Column(name = "surname", nullable = false, length = 24)
    private String surname;

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
    @OneToMany(mappedBy = "clientSender")
    private Collection<Invoice> sends;

    @OneToMany(mappedBy = "clientRecipient")
    private Collection<Invoice> recipes;

    public Client(String firstname, String patronymic, String phone, String surname) {
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.surname = surname;
    }

    public Client(long id, String firstname, String patronymic, String phone, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.surname = surname;
    }

    public Client(long id, String firstname, String patronymic, String phone, String surname, Collection<Station> stations, Collection<Invoice> sends, Collection<Invoice> recipes) {
        this.id = id;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.surname = surname;
        this.stations = stations;
        this.sends = sends;
        this.recipes = recipes;
    }

    public Client(String firstname, String patronymic, String phone, String surname, Collection<Station> stations, Collection<Invoice> sends, Collection<Invoice> recipes) {
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.surname = surname;
        this.stations = stations;
        this.sends = sends;
        this.recipes = recipes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (firstname != null ? !firstname.equals(client.firstname) : client.firstname != null) return false;
        if (patronymic != null ? !patronymic.equals(client.patronymic) : client.patronymic != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (surname != null ? !surname.equals(client.surname) : client.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    public Collection<Station> getStations() {
        return stations;
    }

    public void setStations(Collection<Station> stations) {
        this.stations = stations;
    }

    public Collection<Invoice> getSends() {
        return sends;
    }

    public void setSends(Collection<Invoice> sends) {
        this.sends = sends;
    }

    public Collection<Invoice> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Invoice> recipes) {
        this.recipes = recipes;
    }
}
