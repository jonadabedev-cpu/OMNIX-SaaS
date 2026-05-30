package com.omnix.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendRsvpConfirmation(String to, String guestName, String qrCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("✅ Presença confirmada — OMNIX");
        message.setText(
                "Olá, " + guestName + "!\n\n" +
                "Sua presença foi confirmada com sucesso.\n\n" +
                "Seu QR Code de entrada: " + qrCode + "\n\n" +
                "Apresente este código na entrada do evento.\n\n" +
                "— Equipe OMNIX"
        );
        mailSender.send(message);
    }

    @Override
    public void sendPasswordReset(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("🔐 Recuperação de senha — OMNIX");
        message.setText(
                "Você solicitou a recuperação de senha.\n\n" +
                "Use o token abaixo para redefinir sua senha:\n" +
                token + "\n\n" +
                "Se não foi você, ignore este email.\n\n" +
                "— Equipe OMNIX"
        );
        mailSender.send(message);
    }
}