package br.ueg.progweb1.crud_bike.model;

import br.ueg.progweb1.aula01.model.GenericModel;
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
public  class Bike implements GenericModel<Long> {

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

    @Column(name = "codigo_modelo",  nullable = false, length = 12)
    private String partNumber;

    @Column(name = "descricao",  nullable = false, length = 150)
    private String description;

    @Column(name = "tamanho_quadro")
    private Double sizeFrame;

    @Column(name = "tamanho_roda")
    private Double sizeWheel;

    @Column(name = "eh_mountainbike",  nullable = false)
    private Boolean isMTB;

    @Column(name = "date_manufactured", nullable = false)
    private LocalDate manufacturedDate;

    @Column(name = "date_creation", nullable = false)
    private LocalDate createdDate;
}
