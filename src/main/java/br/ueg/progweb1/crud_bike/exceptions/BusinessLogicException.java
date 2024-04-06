package br.ueg.progweb1.crud_bike.exceptions;

import lombok.Getter;

public @Getter class BusinessLogicException extends RuntimeException{
    private BusinessLogicError error;

    public BusinessLogicException(String message, Throwable e){
        super(message, e);
        this.error = BusinessLogicError.GENERAL;
    }

    public BusinessLogicException(String message){
        super(message);
        this.error = BusinessLogicError.GENERAL;
    }

    public BusinessLogicException(BusinessLogicError be){
        super(be.getMessage());
        this.error = be;
    }
}
