package com.omnix.repositories;

import com.omnix.entities.Subscription;
import com.omnix.entities.User;
import com.omnix.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // Busca assinatura ativa do usuário
    Optional<Subscription> findByUserAndStatus(User user, SubscriptionStatus status);

    // Busca pelo ID do Mercado Pago — usado no webhook de pagamento
    Optional<Subscription> findByMercadoPagoId(String mercadoPagoId);
}