package com.devhub.api.application.service;

import com.devhub.api.domain.model.Resource;
import com.devhub.api.domain.model.Tag;
import com.devhub.api.domain.repository.ResourceRepository;
import com.devhub.api.domain.repository.TagRepository;
import com.devhub.api.web.dto.ResourceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository repository;
    private final TagRepository tagRepository;
    private final ScrapingService scrapingService;


    @CacheEvict(value = "resources", allEntries = true)
    @Transactional
    public Resource save(ResourceRequest request) {
        var metadata = scrapingService.getMetadata(request.url());

        Resource resource = new Resource();
        resource.setUrl(request.url());
        resource.setTitle(metadata.title());
        resource.setDescription(metadata.description());

        if (request.tags() != null && !request.tags().isEmpty()) {
            Set<Tag> managedTags = request.tags().stream()
                    .map(tagName -> tagRepository.findByName(tagName.toLowerCase().trim())
                            .orElseGet(() -> tagRepository.save(new Tag(tagName))))
                    .collect(Collectors.toSet());
            resource.setTags(managedTags);
        }

        return repository.save(resource);
    }

    @Cacheable(value = "resources")
    public List<Resource> findAll() {
        System.out.println("LOG: Buscando no Banco de Dados (PostgreSQL)...");
        return repository.findAll();
    }

    @Cacheable(value = "resources", key = "#tag")
    public List<Resource> findByTag(String tag) {
        System.out.println("LOG: Buscando TAG no Banco de Dados...");
        return repository.findByTags_NameIgnoreCase(tag.trim());
    }
}