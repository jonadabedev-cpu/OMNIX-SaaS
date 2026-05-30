package com.omnix.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QrCodeUtil {

    /**
     * Gera um QR Code único por convidado no formato:
     * OMNIX-{uuid}-event-{eventId}
     *
     * Exemplo: OMNIX-4f8a91d2-event-10
     */
    public static String generate(Long eventId) {
        String uuid = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8); // pega só os 8 primeiros caracteres

        return "OMNIX-" + uuid + "-event-" + eventId;
    }

    /**
     * Valida se o formato do QR Code é válido
     * antes de buscar no banco
     */
    public static boolean isValid(String qrCode) {
        if (qrCode == null || qrCode.isBlank()) {
            return false;
        }
        return qrCode.matches("^OMNIX-[a-zA-Z0-9]+-event-\\d+$");
    }
}