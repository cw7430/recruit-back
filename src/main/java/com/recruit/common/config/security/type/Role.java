package com.recruit.common.config.security.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), GUEST("ROLE_GUEST");

    private final String role;

    public static Role from(String role) {
        if (role == null || role.isBlank()) {
            return Role.GUEST;
        }
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Role.GUEST;
        }
    }
}
