package com.isol.usuarios.entity;

import com.isol.usuarios.utils.UsuarioConstantes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = UsuarioConstantes.REQUIRED_NOMBRE)
    @Column(name = "nombre")
    private String nombreUsuario;

    @NotNull(message = UsuarioConstantes.REQUIRED_APELLIDO)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @NotNull(message = UsuarioConstantes.REQUIRED_EMAIL)
    @Column(name = "email")
    @Email(message = UsuarioConstantes.INVALID_EMAIL)
    private String email;

    @Column(name = "fecha_creacion",updatable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    private void onCreate(){
        this.fechaCreacion = LocalDate.now();
        this.fechaModificacion = LocalDate.now();
    }

    @PreUpdate
    private void onUpdate(){
        this.fechaModificacion = LocalDate.now();
    }

}
