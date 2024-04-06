package br.ueg.progweb1.aula01.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBikeDTO {

    @Schema(description = "Número de série da bike", example = "1234567890AB")
    private String serialNumber;

    @Schema(description = "Descrição do modelo da bike", example = "Quadro 18 polegadas, aro 26, 21 marchas, cor preta")
    private String description;

    @Schema(description = "O modelo é Mountain-Bike?", example = "Sim ou Não")
    private Boolean isMTB;
}
