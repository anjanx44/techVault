package com.anjan.techvault.domain.role;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class RoleRepository implements PanacheRepository<Role> {

    public Optional<Role> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public Role save(Role role) {
        if (role.getId() == null) {
            persist(role);
        } else {
            role = getEntityManager().merge(role);
        }
        return role;
    }
}