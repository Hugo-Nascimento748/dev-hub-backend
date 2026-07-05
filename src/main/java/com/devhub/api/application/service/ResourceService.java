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
        // 1. Usamos o ScrapingService para buscar os dados na internet
        var metadata = scrapingService.getMetadata(request.url());

        // 2. Criamos o objeto Resource com o que veio do Scraping
        Resource resource = new Resource();
        resource.setThumbnailUrl(request.url());
        resource.setTitle(metadata.title());
        resource.setDescription(metadata.description());

        // 3. Processamos as Tags (Busca se já existe, se não, cria uma nova)
        if (request.tags() != null && !request.tags().isEmpty()) {
            Set<Tag> managedTags = request.tags().stream()
                    .map(tagName -> tagRepository.findByName(tagName.toLowerCase().trim())
                            .orElseGet(() -> tagRepository.save(new Tag(tagName))))
                    .collect(Collectors.toSet());
            resource.setTags(managedTags);
        }

        // 4. Salvamos tudo no banco
        return repository.save(resource);
    }

    public List<Resource> findAll() {
        return repository.findAll();
    }
}