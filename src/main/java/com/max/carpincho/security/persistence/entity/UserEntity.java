package com.max.carpincho.security.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "account_no_expired")
    private Boolean accountNoExpired;

    @Column(name = "account_no_locked")
    private Boolean accountNoLocked;

    @Column(name = "credential_no_expired")
    private Boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(
            name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

}
