package com.devhub.api.web.controller;

import com.devhub.api.application.service.ResourceService;
import com.devhub.api.domain.model.Resource;
import com.devhub.api.web.dto.ResourceRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService service;

    @PostMapping
    public ResponseEntity<Resource> create(@RequestBody @Valid ResourceRequest request){
        Resource savedResource = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResource);
    }

    @GetMapping
    public ResponseEntity<List<Resource>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
