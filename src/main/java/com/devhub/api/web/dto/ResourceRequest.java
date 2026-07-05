package com.devhub.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import java.util.Set;

public record ResourceRequest(
        @NotBlank(message = "A URL é obrigatória")
        @URL(message = "URL inválida")
        String url,

        Set<String> tags
) {}