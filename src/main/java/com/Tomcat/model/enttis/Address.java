package com.Tomcat.model.enttis;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @Column(name = "address_id")
    private int addressID;
    private String country;
    private String city;
    private String street;
    @Column(name = "user_id")
    private int userID;
}
