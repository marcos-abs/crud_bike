package br.ueg.progweb1.aula01.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDTO {

    @Schema(description = "Número da matrícula do aluno", example = "1234567890")
    private String registerNumber;

    @Schema(description = "Nome do aluno", example = "Fulano de Tal")
    private String name;

    @Schema(description = "Curso do aluno", example = "Sistemas de Informação")
    private String course;
}
