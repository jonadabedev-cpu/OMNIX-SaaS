package com.omnix.enums;

public enum PlanType {

    //Plano gratuido do OMNIX- pagina simple e links compartilháveis 
    BASIC("Omnix Basico", false, false),

    // Plano intermediário - RSVP e Layouts premius 
    CLASSIC("Omnix Classic", true, false),

    // Plano completo - QR Code, presença e dashboard avançado 
    BLACK("Omnix Black", true, true);

    private final String displayName;
    private final boolean rsvpEnabled;
    private final boolean qrCodeEnabled;

    PlanType(String displayName, boolean rsvpEnable, boolean qrCodeEnable) {
        this.displayName = displayName;
        this.rsvpEnabled = rsvpEnable;
        this.qrCodeEnabled = qrCodeEnable;
    }

    public String getDisplayName() { return displayName; }
    public boolean isRsvpEnbled() { return rsvpEnabled; }
    public boolean isQrCodeEnabled() {return qrCodeEnabled; }

    /**
     * Verificar se este plano tem acesso a um recuso do plano alvo.
     * ex: BLACK.includes(classic) -> true
     */
    public boolean includes(PlanType required) {
        return this.ordinal() >= required.ordinal();
    }
}
