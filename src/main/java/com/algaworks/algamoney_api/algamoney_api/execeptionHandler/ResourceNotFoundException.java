package com.algaworks.algamoney_api.algamoney_api.execeptionHandler;

import org.springframework.validation.BindException;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ResourceNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public BindException getBindingResult() {
        return null;
    }

}
