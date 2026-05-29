package com.omnix.enums;

public enum RsvpStatus {

    //Convidado ainda Não Respondeu (padrão ao ser cadastrado)
    PENDING("Pendente"),

    //Convidado confirmou presença via linl público
    CONFIRMED("Confirmado"),

    //Convidado recusou o convite
    DECLINED("Recusado");

    private final String displayName;

    RsvpStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
