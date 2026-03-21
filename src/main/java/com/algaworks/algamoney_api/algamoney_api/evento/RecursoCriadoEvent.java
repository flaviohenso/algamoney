package com.algaworks.algamoney_api.algamoney_api.evento;

import org.springframework.context.ApplicationEvent;

import jakarta.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Long codigo;
    private String path;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo, String path) {
        super(source);
        this.response = response;
        this.codigo = codigo;
        this.path = path;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;

    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

}
