package com.project.mscars.domain.car.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private long idCar;
    @NotBlank(message = "Brand cannot be empty")
    @Column(name = "brand")
    private String brand;
    @NotBlank(message = "Model cannot be empty")
    @Column(name = "model")
    private String model;
    @OneToOne
    private Pilot pilot;
    @Column(name = "year")
    @DateTimeFormat(pattern = "yyyy")
    private Date year;
}
