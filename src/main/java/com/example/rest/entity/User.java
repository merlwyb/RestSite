package com.example.rest.entity;

import com.example.rest.entity.security.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "sq_user_id", allocationSize = 1)
    private long id;
    private String fullName;
    private String login;
    private String hashPassword;
    private double tokenAmount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String fullName, String login, String hashPassword) {
        this.fullName = fullName;
        this.login = login;
        this.hashPassword = hashPassword;
        this.tokenAmount = 1.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String password) {
        this.hashPassword = password;
    }

    public Double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(Double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
