package com.algaworks.algamoney_api.algamoney_api.config;


import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;


public class SecretClientConfiguration {

    public SecretClient secretClient() {
        return new SecretClientBuilder()
            .vaultUrl(System.getenv("APP_CONFIGURATION_ENDPOINT"))
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
    }

}
