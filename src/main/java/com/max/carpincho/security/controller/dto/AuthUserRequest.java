package com.max.carpincho.security.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUserRequest(@NotBlank String username,
                              @NotBlank String password) {
}
