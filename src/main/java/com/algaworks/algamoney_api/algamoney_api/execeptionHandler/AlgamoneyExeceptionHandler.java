package com.algaworks.algamoney_api.algamoney_api.execeptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // observa toda a aplicação
public class AlgamoneyExeceptionHandler extends ResponseEntityExceptionHandler{

    /*
     * Injetado o message.properties para pegar a mensagem de erro
     */
    @Autowired
    private MessageSource messageSource;

    /*
     * Método para tratar a execão de atributo no body request que não existe na classe modelo
     */
     @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemDesenvolvedor = ex.toString();

        List<Erro> erros = Arrays.asList(
            new Erro(this.messageSource.getMessage("mensagem.invalida",null,
                    LocaleContextHolder.getLocale()), mensagemDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /*
     * Metodo para tratar quando uma categoria não for encontrada na base de dados
     */
    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(ResourceNotFoundException ex,
            WebRequest request) {

        String mensagemDesenvolvedor = ex.toString();

        List<Erro> erros = Arrays.asList(
            new Erro(this.messageSource.getMessage("recurso.nao-encontrado",null,
                    LocaleContextHolder.getLocale()), mensagemDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /*
     * Metodo para tratar quando já existir um registro cadastrado na base de dados
     */
    @ExceptionHandler({ ResourceConflictException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(ResourceConflictException ex,
            WebRequest request) {

        String mensagemDesenvolvedor = ex.toString();

        List<Erro> erros = Arrays.asList(
            new Erro(this.messageSource.getMessage("recurso.ja-cadastrado",null,
                    LocaleContextHolder.getLocale()), mensagemDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /*
     * Metodo para tratar quando ocorrer erro de validaçao de atributos
     */
     @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erros = criarListaDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /* Método para criar uma lista de erro recebendo como parametros um BindingResult interando sobre os FieldErros*/
    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }

    /*
     * Classe para tratar as mensagens de erro
     */
    public static class Erro {

        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario) {
            this.mensagemUsuario = mensagemUsuario;
        }

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }

}
