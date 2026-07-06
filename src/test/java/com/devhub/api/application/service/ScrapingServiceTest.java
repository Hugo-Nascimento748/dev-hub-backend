package com.devhub.api.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ScrapingServiceTest {

    @Autowired
    private ScrapingService scrapingService;

    @Test
    @DisplayName("Deve extrair título de uma URL real com sucesso")
    void shouldExtractTitleFromRealUrl(){

        String url = "https://www.wikipedia.org";

        var metadata = scrapingService.getMetadata(url);

        assertNotNull(metadata);
        assertEquals("Wikipedia", metadata.title());
        System.out.println("Título encontrado: " + metadata.title());
    }

    @Test
    @DisplayName("Deve retornar fallback quando a URL for inválida")
    void shouldReturnFallbackWhenUrlIsInvalid(){

        String url = "https://url-inexistente.com";

        var metadata = scrapingService.getMetadata(url);

        assertEquals("Título Indisponível", metadata.title());
    }
}
