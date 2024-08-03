package com.roman31X.zona_fit_GYM_JSF.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity               //implementa el JPA jakarta.persistence.API tipo Entity
@Data                 //implementa los setter y getter
@NoArgsConstructor    //Constructor sin argumentos
@AllArgsConstructor   //Constructor con argumentos
@ToString             //Implementa el método ToString para mensaje de atributos
@EqualsAndHashCode    //Implementa método de evaluación de variables

public class Cliente {
    @Id                                                  //Identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Indicamos Llave identificador
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;
}
