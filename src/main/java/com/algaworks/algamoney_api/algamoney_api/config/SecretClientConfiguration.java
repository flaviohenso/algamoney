package com.algaworks.algamoney_api.algamoney_api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

@Profile("prod")
public class SecretClientConfiguration {

    @Bean
    public SecretClient secretClient() {
        return new SecretClientBuilder()
            .vaultUrl(System.getenv("APP_CONFIGURATION_ENDPOINT"))
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
    }

}
