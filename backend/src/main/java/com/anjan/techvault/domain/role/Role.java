package com.anjan.techvault.domain.role;

import com.anjan.techvault.domain.user.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Role name, e.g., ADMIN, AUTHOR, READER

    @ManyToMany(mappedBy = "roles")
    private Set<User> users; // Users associated with this role

    // No-Args Constructor (Required by JPA)
    public Role() {}

    // All-Args Constructor
    public Role(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
