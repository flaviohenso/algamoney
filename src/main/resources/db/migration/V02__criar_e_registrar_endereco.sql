CREATE TABLE pessoa (
    id bigint NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(255) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

INSERT INTO pessoa (nome, cpf, email, telefone, logradouro, numero, complemento, cidade, estado, cep, bairro) VALUES ('João', '12345678901', 'joao@joao.com.br', '11999999999', 'Rua João', '123', 'Apto 123', 'São Paulo', 'SP', '12345678', 'Centro');
INSERT INTO pessoa (nome, cpf, email, telefone, logradouro, numero, complemento, cidade, estado, cep, bairro) VALUES ('Maria', '98765432101', 'maria@maria.com.br', '11999999999', 'Rua Maria', '123', 'Apto 123', 'São Paulo', 'SP', '12345678', 'Centro');
INSERT INTO pessoa (nome, cpf, email, telefone, logradouro, numero, complemento, cidade, estado, cep, bairro) VALUES ('José', '12345678902', 'jose@jose.com.br', '11999999999', 'Rua José', '123', 'Apto 123', 'São Paulo', 'SP', '12345678', 'Centro');