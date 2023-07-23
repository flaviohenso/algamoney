package com.algaworks.algamoney_api.algamoney_api.execeptionHandler;

public class ResourceConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceConflictException(String mensagem) {
        super(mensagem);
    }

    public ResourceConflictException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
