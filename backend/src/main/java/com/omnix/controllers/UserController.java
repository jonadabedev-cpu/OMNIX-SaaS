package com.omnix.controllers;

import com.omnix.dto.UserResponse;
import com.omnix.entities.User;
import com.omnix.services.UserService;
import com.omnix.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET /api/users/me
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getProfile(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                ApiResponse.success("Perfil encontrado.",
                        userService.getProfile(user)));
    }

    // PUT /api/users/me/name?newName=João
    @PutMapping("/me/name")
    public ResponseEntity<ApiResponse<UserResponse>> updateName(
            @AuthenticationPrincipal User user,
            @RequestParam String newName) {

        return ResponseEntity.ok(
                ApiResponse.success("Nome atualizado.",
                        userService.updateName(user, newName)));
    }

    // DELETE /api/users/me
    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(
            @AuthenticationPrincipal User user) {

        userService.deleteAccount(user);
        return ResponseEntity.ok(
                ApiResponse.success("Conta deletada com sucesso."));
    }
}