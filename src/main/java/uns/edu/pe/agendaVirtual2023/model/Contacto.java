/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uns.edu.pe.agendaVirtual2023.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author USER
 */
@Entity
@Getter
@Setter
public class Contacto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontacto")
    private Integer id;
    
    @NotBlank(message = "Este campo es obligatorio")
    private String nombre;
    
    @PastOrPresent(message = "El valor no debe estar en futuro")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fechanac")
    private LocalDate fechaNacimiento;
    
    @Size(max = 15, message = "El valor debe tener 15 caracteres como máximo")
    private String celular;
    
    @Email(message = "El email tiene un formato inválido")
    private String email;
    
    @Column(name = "fechareg")
    private LocalDateTime fechaRegistro;
    
}
