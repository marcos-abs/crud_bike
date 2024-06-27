package br.ueg.progweb1.crud_bike.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeDTO {

    @Schema(description = "Chave do registro da bike", example = "1")
    private Long id;

    @Schema(description = "Código do modelo da bike", example = "1234567890AB")
    private String partNumber;

    @Schema(description = "Descrição do modelo da bike", example = "Bicicleta Urbana, Marca Giant, 21 marchas, cor preta")
    private String description;

    @Schema(description = "Tamanho do quadro da bike", example = "17.5 polegadas")
    private Double sizeFrame;

    @Schema(description = "Tamanho da roda da bike", example = "27.5 polegadas")
    private Double sizeWheel;

    @Schema(description = "O modelo é Mountain-Bike?", example = "Sim ou Não")
    private Boolean isMTB;

    @Schema(description = "Data de fabricação do modelo da bike", example = "01/01/1980")
    private LocalDate manufacturedDate;

    @Schema(description = "Data de criação do registro", example = "01/01/1980")
    private LocalDate createdDate;
}
