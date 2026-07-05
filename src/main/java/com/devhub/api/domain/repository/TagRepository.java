package com.devhub.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devhub.api.domain.model.Tag;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
