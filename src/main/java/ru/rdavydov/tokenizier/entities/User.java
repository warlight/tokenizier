package ru.rdavydov.tokenizier.entities;

import ru.rdavydov.tokenizier.utils.Hasher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String password;

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public User(String name, String email, String password) throws Exception {
        this.name = name;
        this.email = email;
        this.password = Hasher.md5(password);
    }

    public String getName() {
        return name;
    }
}
