package br.ueg.progweb1.crud_bike.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBikeDTO {
    @Schema(description = "Descrição da Bike")
    private String description;

    @Schema(description = "O modelo é Mountain-Bike?", example = "Sim ou Não")
    private Boolean isMTB;
}
