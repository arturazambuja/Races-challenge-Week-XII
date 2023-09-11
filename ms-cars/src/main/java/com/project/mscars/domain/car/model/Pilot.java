package com.project.mscars.domain.car.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pilots")
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pilot", nullable = false, unique = true)
    @JsonIgnore
    private long idPilot;
    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @OneToOne
    @JsonIgnore
    private Car car;
}
