package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity(name = "sender_recipient_package")
@Immutable
@Data
@NoArgsConstructor
public class ClientsPackages {

    @EmbeddedId
    protected Id id = new Id();

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    static class Id implements Serializable {
        @Column(name = "client_sender_id")
        private long senderId;
        @Column(name = "client_recipient_id")
        private long recipientId;
        @Column(name = "package_id")
        private long packageId;
    }

    @NotNull
    @NonNull
    @ManyToOne
    @JoinColumn(
            name = "client_sender_id",nullable = false,
            insertable = false,updatable = false
    )
    protected Client sender;

    @NotNull
    @NonNull
    @ManyToOne
    @JoinColumn(
            name = "client_recipient_id",nullable = false,
            insertable = false,updatable = false
    )
    protected Client recipient;

    @NotNull
    @NonNull
    @ManyToOne
    @JoinColumn(
            name = "package_id",nullable = false,
            insertable = false,updatable = false
    )
    protected Package packages;

    public ClientsPackages(@NonNull Client sender, @NonNull Client recipient, @NonNull Package packages) {
        this.sender = sender;
        this.recipient = recipient;
        this.packages = packages;
        this.id.packageId= packages.getId();
        this.id.senderId= sender.getId();
        this.id.recipientId= recipient.getId();
    }
}



