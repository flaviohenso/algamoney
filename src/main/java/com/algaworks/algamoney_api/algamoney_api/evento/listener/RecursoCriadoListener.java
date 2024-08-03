package com.algaworks.algamoney_api.algamoney_api.evento.listener;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney_api.algamoney_api.evento.RecursoCriadoEvent;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RecursoCriadoListener.class);

    public RecursoCriadoListener() {
    }

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {

        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Long codigo = recursoCriadoEvent.getCodigo();

        response.setHeader("Location", defineUri("/pessoa/listar/", codigo).toASCIIString());
        response.setStatus(HttpServletResponse.SC_CREATED);
        logger.info("Recurso criado com sucesso");
    }

    /**
     * Método para definir a uri
     *
     * @param path
     * @param id
     * @return
     */
    private URI defineUri(String path, Long id) {
        if (id != null && id != 0) {
            return ServletUriComponentsBuilder.fromCurrentContextPath().path(path + id)
                    .buildAndExpand(id).toUri();
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(path)
                .buildAndExpand().toUri();
    }

}
