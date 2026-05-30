package com.omnix.services;

import com.omnix.dto.UserResponse;
import com.omnix.entities.User;
import com.omnix.enums.PlanType;
import com.omnix.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getProfile(User user) {
        return UserResponse.from(user);
    }

    @Override
    public UserResponse updateName(User user, String newName) {
        user.setName(newName);
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    public UserResponse updatePlan(User user, PlanType plan) {
        user.setPlan(plan);
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    public void deleteAccount(User user) {
        userRepository.delete(user);
    }
}