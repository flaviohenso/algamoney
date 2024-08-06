package com.algaworks.algamoney_api.algamoney_api.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

@Configuration
public class DataSourceConfing {

    private final SecretClient secretClient;

	public DataSourceConfing(SecretClient secretClient) {
		this.secretClient = secretClient;
	}

    @Bean
    @Primary // Use esta anotação se tiver vários DataSources
    public DataSource dataSource() {
        return DataSourceBuilder.create()
        .url("jdbc:mysql://localhost:3306/"+System.getenv("NAMEDB")+"?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true")
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .password(getSecretValue())
        .username(System.getenv("USERNAMEDB"))
        .build(); 
    }

    /*
     * Método que retorna a senha do banco de dados
     */
    private String getSecretValue() {
        String secretValue = System.getenv("NAMEDB");
		KeyVaultSecret secret = secretClient.getSecret(secretValue);
        return secret.getValue();
    }

}
