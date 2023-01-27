package ru.lytvest.kafedra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users_data")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    @Column(nullable = false, unique = true)
    public String username;

    public String password;

    public String fio = "";
    public String roles = "USER";

    public Boolean locked = false;

    public Boolean confirmed = false;

}
