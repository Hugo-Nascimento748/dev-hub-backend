package com.devhub.api.application.service;

import com.devhub.api.domain.model.Resource;
import com.devhub.api.domain.model.Tag;
import com.devhub.api.domain.repository.ResourceRepository;
import com.devhub.api.domain.repository.TagRepository;
import com.devhub.api.web.dto.ResourceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Isso injeta automaticamente os Repositories e o Service abaixo
public class ResourceService {

    private final ResourceRepository repository;
    private final TagRepository tagRepository; // Faltava injetar este
    private final ScrapingService scrapingService; // Faltava injetar este

    @Transactional
    public Resource save(ResourceRequest request) {
        var metadata = scrapingService.getMetadata(request.url());

        Resource resource = new Resource();
        resource.setUrl(request.url()); // O link que o usuário enviou
        resource.setTitle(metadata.title()); // Título real do site
        resource.setDescription(metadata.description()); // Descrição real do site

        if (request.tags() != null && !request.tags().isEmpty()) {
            Set<Tag> managedTags = request.tags().stream()
                    .map(tagName -> tagRepository.findByName(tagName.toLowerCase().trim())
                            .orElseGet(() -> tagRepository.save(new Tag(tagName))))
                    .collect(Collectors.toSet());
            resource.setTags(managedTags);
        }

        return repository.save(resource);
    }

    public List<Resource> findAll() {
        return repository.findAll();
    }
}