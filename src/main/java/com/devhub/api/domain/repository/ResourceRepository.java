package com.devhub.api.domain.repository;

import com.devhub.api.domain.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByTags_NameIgnoreCase(String tagName);
}
