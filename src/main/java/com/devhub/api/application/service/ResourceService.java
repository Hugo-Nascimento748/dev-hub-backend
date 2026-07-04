package com.devhub.api.application.service;

import com.devhub.api.domain.model.Resource;
import com.devhub.api.domain.repository.ResourceRepository;
import com.devhub.api.web.dto.ResourceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository repository;

    @Transactional
    public Resource save(ResourceRequest request){
        Resource resource = new Resource();
        resource.setTitle(request.title());
        resource.setDescription(request.description());
        resource.setThumbnailUrl(request.url());

        return repository.save(resource);
        }

        public List<Resource> findAll(){
        return repository.findAll();
    }
}
