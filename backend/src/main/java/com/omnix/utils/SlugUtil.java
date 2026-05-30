package com.omnix.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.UUID;

@Component
public class SlugUtil {

    /**
     * Converte o título do evento em uma URL amigável.
     *
     * "Casamento João e Maria" → "casamento-joao-e-maria"
     * "Festa de 15 anos!"     → "festa-de-15-anos"
     */
    public static String generate(String title) {
        if (title == null || title.isBlank()) {
            return UUID.randomUUID().toString();
        }

        return Normalizer
                .normalize(title, Normalizer.Form.NFD)  // separa acentos das letras
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "") // remove acentos
                .toLowerCase()                              // tudo minúsculo
                .replaceAll("[^a-z0-9\\s-]", "")          // remove caracteres especiais
                .trim()                                     // remove espaços nas bordas
                .replaceAll("\\s+", "-");                 // espaços viram hífens
    }

    /**
     * Gera slug único adicionando sufixo aleatório
     * caso o slug já exista no banco.
     *
     * "casamento-joao-e-maria" → "casamento-joao-e-maria-a3f2"
     */
    public static String generateUnique(String title) {
        String base = generate(title);
        String suffix = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 4);
        return base + "-" + suffix;
    }
}