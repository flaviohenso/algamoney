package com.algaworks.algamoney_api.algamoney_api.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

@Configuration
public class DataSourceConfing {

    @Bean
    @Profile("dev")
    public DataSource dataSourceDev() {
        System.out.println("configuração de desenvolvimento");
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/" + System.getenv("NAMEDB")
                        + "?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .password("spring")
                .username(System.getenv("USERNAMEDB"))
                .build();
    }

    @Bean
    @Profile("prod")
    public DataSource dataSource(SecretClient secretClientProd) {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/" + System.getenv("NAMEDB")
                        + "?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .password(getSecretValue(secretClientProd))
                .username(System.getenv("USERNAMEDB"))
                .build();
    }

    /*
     * Método que retorna a senha do banco de dados
     */
    private String getSecretValue(SecretClient secretClientProd) {
        String secretValue = System.getenv("NAMEDB");
        KeyVaultSecret secret = secretClientProd.getSecret(secretValue);
        return secret.getValue();
    }

}
