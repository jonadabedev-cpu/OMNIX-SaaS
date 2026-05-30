package com.omnix.services;

public interface EmailService {
    void sendRsvpConfirmation(String to, String guestName, String qrCode);
    void sendPasswordReset(String to, String token);
}