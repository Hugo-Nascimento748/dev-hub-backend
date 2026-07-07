package com.devhub.api.web.controller;

import com.devhub.api.application.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{resourceId}")
    public ResponseEntity<Void> vote(
            @PathVariable Long resourceId,
            @RequestParam Long userId, // Temporário até termos o login real
            @RequestParam Integer value // 1 ou -1
    ) {
        voteService.vote(resourceId, userId, value);
        return ResponseEntity.ok().build();
    }
}