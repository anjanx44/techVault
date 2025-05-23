package com.anjan.techvault.domain.tag;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class TagRepository implements PanacheRepository<Tag> {

    public Optional<Tag> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public Tag save(Tag tag) {
        if (tag.getId() == null) {
            persist(tag);
        } else {
            tag = getEntityManager().merge(tag);
        }
        return tag;
    }
}