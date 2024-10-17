package com.Tomcat.model.enttis;


import com.Tomcat.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;
    private String name;
    private String surname;
    private int year;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "verify_code")
    private String verifyCode;
    @Column(name = "reset_token")
    private String resetToken;
}

