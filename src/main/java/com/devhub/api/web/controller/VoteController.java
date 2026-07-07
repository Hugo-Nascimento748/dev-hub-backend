package com.devhub.api.web.controller;

import com.devhub.api.application.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{resourceId}")
    public ResponseEntity<Void> vote(
            @PathVariable Long resourceId,
            @RequestParam Integer value,
            @AuthenticationPrincipal OAuth2User principal // O Spring injeta o usuário logado aqui
    ) {
        // Pegamos o ID do GitHub direto da sessão segura
        String githubId = principal.getAttribute("id").toString();

        voteService.vote(resourceId, githubId, value);
        return ResponseEntity.ok().build();
    }
}