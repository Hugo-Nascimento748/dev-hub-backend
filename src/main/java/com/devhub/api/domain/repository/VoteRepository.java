package com.devhub.api.domain.repository;

import com.devhub.api.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUser_IdAndResource_Id(Long userId, Long resourceId);
}
