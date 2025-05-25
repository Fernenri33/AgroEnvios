package com.AgroEnvios.apiAE.Enums;

public enum Estado {
    
    Pendiente("Pendiente"),
    En_Revision("En Revisión"),
    Rechazado("Rechazado"),
    Entregado("Entregado"),
    Aceptado("Aceptado");

    private final String displayName;

    // Constructor to set the display name
    Estado(String displayName) {
        this.displayName = displayName;
    }

    // Override toString to return the display name
    @Override
    public String toString() {
        return this.displayName;
    }
}
