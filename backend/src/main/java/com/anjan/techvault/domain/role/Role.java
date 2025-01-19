package com.anjan.techvault.domain.role;

import com.anjan.techvault.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Role name, e.g., ADMIN, AUTHOR, READER

    @ManyToMany(mappedBy = "roles")
    private Set<User> users; // Users associated with this role

}

