package com.omnix.enums;

public enum PlanType {

    BASIC("Omnix Basic",   false, false, 2),
    CLASSIC("Omnix Classic", true,  false, 6),
    BLACK("Omnix Black",   true,  true,  10);

    private final String displayName;
    private final boolean rsvpEnabled;
    private final boolean qrCodeEnabled;
    private final int maxTemplates; // quantidade máxima de templates

    PlanType(String displayName, boolean rsvpEnabled,
             boolean qrCodeEnabled, int maxTemplates) {
        this.displayName   = displayName;
        this.rsvpEnabled   = rsvpEnabled;
        this.qrCodeEnabled = qrCodeEnabled;
        this.maxTemplates  = maxTemplates;
    }

    public String getDisplayName()   { return displayName; }
    public boolean isRsvpEnabled()   { return rsvpEnabled; }
    public boolean isQrCodeEnabled() { return qrCodeEnabled; }
    public int getMaxTemplates()     { return maxTemplates; }

    // Verifica se o plano permite usar o template escolhido
    public boolean canUseTemplate(int templateId) {
        return templateId >= 1 && templateId <= maxTemplates;
    }

    public boolean includes(PlanType required) {
        return this.ordinal() >= required.ordinal();
    }
}