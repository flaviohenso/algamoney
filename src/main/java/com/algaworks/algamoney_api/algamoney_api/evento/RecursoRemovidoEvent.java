package com.algaworks.algamoney_api.algamoney_api.evento;



import org.springframework.context.ApplicationEvent;

import jakarta.servlet.http.HttpServletResponse;

public class RecursoRemovidoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;

    /*
     * Construtor
     */
    public RecursoRemovidoEvent(Object source, HttpServletResponse response) {
        super(source);
        this.response = response;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public RecursoRemovidoEvent(Object source) {
        super(source);
    }

}
