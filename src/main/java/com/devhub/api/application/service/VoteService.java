package com.devhub.api.application.service;

import com.devhub.api.domain.model.*;
import com.devhub.api.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Transactional
    public void vote(Long resourceId, Long userId, Integer value) {
        // 1. Busca as entidades (ou lança erro se não achar)
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. Verifica se o usuário já votou neste recurso
        var existingVote = voteRepository.findByUserIdAndResourceId(userId, resourceId);

        if (existingVote.isPresent()) {
            Vote vote = existingVote.get();
            if (vote.getVoteValue().equals(value)) {
                // Se o voto é igual ao anterior, o usuário clicou de novo para CANCELAR
                resource.setVoteCount(resource.getVoteCount() - value);
                voteRepository.delete(vote);
            } else {
                // Se o voto é diferente (mudou de up para down), atualiza
                resource.setVoteCount(resource.getVoteCount() + (value * 2));
                vote.setVoteValue(value);
                voteRepository.save(vote);
            }
        } else {
            // 3. Se não existe voto, cria um novo
            Vote newVote = new Vote(null, user, resource, value);
            resource.setVoteCount(resource.getVoteCount() + value);
            voteRepository.save(newVote);
        }

        resourceRepository.save(resource);
    }
}