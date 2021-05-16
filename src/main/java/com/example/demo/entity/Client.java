package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "client")
@Data
@NoArgsConstructor
@Table(name = "client", catalog ="postOffice",schema = "public")
@AttributeOverrides({
        @AttributeOverride(name = "firstname",              column = @Column(name = "firstname",      table = "client", nullable = false, length = 12)),
        @AttributeOverride(name = "middlename",             column = @Column(name = "middlename",     table = "client", nullable = false, length = 12)),
        @AttributeOverride(name = "lastname",               column = @Column(name = "lastname",       table = "client", nullable = false, length = 12)),
        @AttributeOverride(name = "phoneNumber.phone",      column = @Column(name = "phone_number",   table = "client", nullable = false, length = 12))
})
public class Client extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", table = "client")
    protected Long id;

    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "region",             column = @Column(name = "region",               table = "client", length = 24)),
            @AttributeOverride(name = "city",               column = @Column(name = "city",                 table = "client", length = 24)),
            @AttributeOverride(name = "street",             column = @Column(name = "street",               table = "client", length = 24)),
            @AttributeOverride(name = "postOfficeNumber",   column = @Column(name = "post_office_number",   table = "client", length = 3))
    })
    protected Address address;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client")
    protected Collection<Package> packages = new ArrayList<>();

    public Client(String firstname, String middlename, String lastname, PhoneNumber phoneNumber, @NotNull Address address) {
        super(firstname, middlename, lastname, phoneNumber);
        this.address = address;
    }

    public Client(String firstname, String middlename, String lastname, PhoneNumber phoneNumber) {
        super(firstname, middlename, lastname, phoneNumber);
    }

    public void setPackages(Collection<Package> packages) {
        this.packages = packages;
    }

    public void setPackages(Package ... packages){
        this.packages = Arrays.asList(packages);
    }
}


