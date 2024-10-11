package org.emporio.sabor.real.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "credential", catalog = "emporio_sabor_real", schema = "emporio_sabor_real")
public class CredentialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "email", nullable = false, columnDefinition = "text")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "text")
    private String password;
}
