package com.omnix.enums;

public enum SubscriptionStatus {

    // Pagamento pendentes - Pix gerado mas ainda não pago
    PENDING("Pendente"),

    // Pagamento confimado - plano liberado 
    ACTIVE("Ativa"),

    // Pagamento Recusado ou falhou 
    FAILED("Falhou");

    private final String displayName;

    SubscriptionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Verificar se a assinatura está ativa
    public boolean isActive() {
        return this == ACTIVE;
    }
}
