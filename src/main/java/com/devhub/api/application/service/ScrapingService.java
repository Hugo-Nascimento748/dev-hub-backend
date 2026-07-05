package com.devhub.api.application.service;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ScrapingService {

    public Metadata getMetadata(String url) {
        try {
            // Timeout de 5 segundos para não travar a aplicação
            var document = Jsoup.connect(url).timeout(5000).get();
            String title = document.title();
            String description = document.select("meta[name=description]").attr("content");

            return new Metadata(title, description);
        } catch (IOException e) {
            return new Metadata("Título Indisponível", "Não foi possível extrair a descrição.");
        }
    }

    public record Metadata(String title, String description) {}
}