package br.ueg.progweb1.crud_bike.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchBikeDTO {
    @Schema(description = "Código do modelo da bike")
    private String partNumber;

    @Schema(description = "Descrição do modelo da bike")
    private String description;

    @Schema(description = "Data de Fabricação do modelo da bike")
    private LocalDate manufacturedDate;
}
