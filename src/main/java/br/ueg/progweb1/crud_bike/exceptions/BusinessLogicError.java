package br.ueg.progweb1.crud_bike.exceptions;

import lombok.Getter;

@Getter
public enum BusinessLogicError {

    GENERAL(0L, "Erro desconhecido"),
    NOT_FOUND(1L, "Registro não encontrado"),
    ALREADY_EXISTS(2L, "Registro já existe"),
    MANDATORY_FIELD_NOT_FOUND(3L, "Campo obrigatório não informado"),
    INCORRECT_VALUES(4L, "Valor informado é inválido para este campo"),
    INVALID_KEY(5L, "Chave inválida");

    private Long id;
    private String message;

    BusinessLogicError(long id, String message) {
        this.id = id;
        this.message = message;
    }
}
