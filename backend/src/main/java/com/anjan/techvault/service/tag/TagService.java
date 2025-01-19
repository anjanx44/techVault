package com.anjan.techvault.service.tag;

import com.anjan.techvault.domain.tag.Tag;

import java.util.Optional;

public interface TagService {
    Tag createTag(Tag tag);
    Optional<Tag> getTagByName(String name);
}
