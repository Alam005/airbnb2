package com.airbnb.airbin2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @JsonIgnore
    @Column(name = "mobile_number", nullable = false, unique = true, length = 10)
    private String mobileNumber;

    @JsonIgnore
    @Column(name = "user_roles", length = 30)
    private String userRoles;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

}