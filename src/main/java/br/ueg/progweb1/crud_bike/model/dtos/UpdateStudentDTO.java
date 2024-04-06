package br.ueg.progweb1.aula01.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDTO {
    @Schema(description = "Nome do aluno")
    private String name;

    @Schema(description = "Curso do aluno")
    private String course;
}
