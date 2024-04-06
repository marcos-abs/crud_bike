package br.ueg.progweb1.crud_bike.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBikeDTO {
    @Schema(description = "Código do modelo da bike")
    private String partNumber;

    @Schema(description = "Descrição do modelo da bike")
    private String description;

    @Schema(description = "Tamanho do quadro da bike")
    private Double sizeFrame;

    @Schema(description = "Tamanho da roda da bike")
    private Double sizeWheel;

    @Schema(description = "O modelo é Mountain-Bike?")
    private Boolean isMTB;
}
