CREATE TABLE categoria (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO categoria (nome,codigo) VALUES ('Lazer',1);
INSERT INTO categoria (nome,codigo) VALUES ('Alimentação',2);
INSERT INTO categoria (nome,codigo) VALUES ('Viagem',3);
INSERT INTO categoria (nome,codigo) VALUES ('Saúde',4);
INSERT INTO categoria (nome,codigo) VALUES ('Educação',5);
INSERT INTO categoria (nome,codigo) VALUES ('Transporte',6);