DROP TABLE pedido;
DROP TABLE usuario;
DROP TABLE promocao;

CREATE TABLE usuario (
    id_usuario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome_completo VARCHAR(200) NOT NULL,
    nome_usuario VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(20) NOT NULL    
);

CREATE TABLE promocao (
    id_promocao INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    codigo VARCHAR(50) NOT NULL,
    desconto DECIMAL(10,1)
);

CREATE TABLE pedido (
    id_pedido INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_usuario INTEGER NOT NULL,
    id_promocao INTEGER NOT NULL,
    titulo VARCHAR(50) NOT NULL,
    estado VARCHAR(15) NOT NULL,    
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_promocao) REFERENCES promocao(id_promocao) ON DELETE CASCADE
);

INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Admin', 'admin', 'admin@gmail.com', 'admin');
INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Douglas Baumgratz', 'doug', 'douglas@gmail.com', '123');
INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Jonas', 'jon', 'jonas@gmail.com', '123');
INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Allan', 'all', 'allan@gmail.com', '123');

INSERT INTO promocao(codigo, desconto) VALUES ('COMPRAEMDOBRO', 0.5);
INSERT INTO promocao(codigo, desconto) VALUES ('DEZPORCENTO', 0.9);

INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (1, 1, 'X-Bacon', 'Pendente');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (2, 2, 'X-Salada', 'Em Produção');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (3, 1, 'Hamburguer Simples', 'Encaminhado');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (4, 2, 'X-Egg', 'Entregue');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (4, 1, 'X-egg', 'Cancelado');
