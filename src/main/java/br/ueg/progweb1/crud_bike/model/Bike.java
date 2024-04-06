package br.ueg.progweb1.aula01.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BIKE")
public  class Bike {

    @Id
    @SequenceGenerator(
            name="bike_sequence",
            sequenceName = "bike_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "bike_sequence"
    )
    @Column(name = "chave", nullable = false)
    private Long id;

    @Column(name = "numero_serie",  nullable = false, length = 12)
    private String serialNumber;

    @Column(name = "descricao",  nullable = false, length = 150)
    private String description;

    @Column(name = "eh_mountainbike")
    private Boolean isMTB;

    @Column(name = "date_creation", nullable = false)
    private LocalDate createdDate;
}