package com.devhub.api.domain.repository;

import com.devhub.api.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.resource.id = :resourceId")
    Optional<Vote> findByUserIdAndResourceId(@Param("userId") Long userId, @Param("resourceId") Long resourceId);
}
