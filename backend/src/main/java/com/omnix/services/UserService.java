package com.omnix.services;

import com.omnix.dto.UserResponse;
import com.omnix.entities.User;
import com.omnix.enums.PlanType;

public interface UserService {

    // Busca o perfil do usuário logado
    UserResponse getProfile(User user);

    // Atualiza o nome do usuário
    UserResponse updateName(User user, String newName);

    // Atualiza o plano do usuário após pagamento aprovado
    UserResponse updatePlan(User user, PlanType plan);

    // Deleta a conta do usuário
    void deleteAccount(User user);
}