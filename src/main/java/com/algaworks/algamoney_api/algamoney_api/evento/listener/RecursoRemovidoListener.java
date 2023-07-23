package com.algaworks.algamoney_api.algamoney_api.evento.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney_api.algamoney_api.evento.RecursoRemovidoEvent;

@Component
public class RecursoRemovidoListener implements ApplicationListener<RecursoRemovidoEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RecursoRemovidoListener.class);

    @Override
    public void onApplicationEvent(RecursoRemovidoEvent recursoRemovidoEvent) {

        HttpServletResponse response = recursoRemovidoEvent.getResponse();

        response.setHeader("Location", defineUri("/pessoa/criar").toASCIIString());
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        logger.info("Recurso removido com sucesso");
    }

    /**
     * Método para definir a uri
     *
     * @param path
     * @param id
     * @return
     */
    private URI defineUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(path)
                .buildAndExpand().toUri();
    }
}
