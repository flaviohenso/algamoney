CREATE TABLE lancamento (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    codigo BIGINT(20) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    id_categoria BIGINT(20) NOT NULL,
    id_pessoa BIGINT(20) NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria (codigo),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa (id),
    PRIMARY KEY (id, codigo, id_categoria, id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (codigo, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) VALUES (1,'Salário', '2018-06-01', '2018-06-01', 5000.00, 'Recebimento de salário', 'RECEITA', 1, 1);
INSERT INTO lancamento (codigo, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) VALUES (2,'Aluguel', '2018-06-10', '2018-06-10', 1234.00, 'Aluguel referente a março', 'DESPESA', 2, 2);
INSERT INTO lancamento (codigo, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) VALUES (3,'Aluguel', '2018-06-10', '2018-06-10', 1234.00, 'Aluguel referente a março', 'DESPESA', 3, 3);
INSERT INTO lancamento (codigo, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) VALUES (4,'Aluguel', '2018-06-10', '2018-06-10', 1234.00, 'Aluguel referente a março', 'DESPESA', 4, 1);
INSERT INTO lancamento (codigo, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) VALUES (5,'Aluguel', '2018-06-10', '2018-06-10', 1234.00, 'Aluguel referente a março', 'DESPESA', 5, 2);
INSERT INTO lancamento (codigo, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) VALUES (6,'Aluguel', '2018-06-10', '2018-06-10', 1234.00, 'Aluguel referente a março', 'DESPESA', 6, 3);
