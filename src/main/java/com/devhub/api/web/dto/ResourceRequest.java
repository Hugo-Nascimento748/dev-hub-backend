package com.devhub.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ResourceRequest(
        @NotBlank(message = "O título é obrigatório")
        String title,

        String description,

        @NotBlank(message = "A URL é obrigatória")
        @URL(message = "URL inválida")
        String url
){}
