package br.ueg.progweb1.crud_bike.exceptions;

public class DataException extends RuntimeException{
    public DataException(String message, Throwable e){
        super(message, e);
    }
    public DataException(String message){
        super(message);
    }
}
