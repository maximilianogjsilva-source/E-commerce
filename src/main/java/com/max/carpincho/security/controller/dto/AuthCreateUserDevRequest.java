package com.max.carpincho.security.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AuthCreateUserDevRequest(@NotBlank String username,
                                       @NotBlank String password,
                                       @Size(max = 3, message = "The user cannot have more than 3 roles")
                                    @Valid List<String> roles) {
}
