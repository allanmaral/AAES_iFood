DROP TABLE componente_pedido;
DROP TABLE pedido;
DROP TABLE usuario;
DROP TABLE promocao;
DROP TABLE componente;

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

CREATE TABLE componente (
    id_componente INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    e_produto BOOLEAN NOT NULL,
    preco DECIMAL NOT NULL,
    titulo VARCHAR(50) NOT NULL,
    descricao VARCHAR(256) NOT NULL,
    id_pai INTEGER,
    FOREIGN KEY (id_pai) REFERENCES componente(id_componente) ON DELETE CASCADE
);

CREATE TABLE componente_pedido (
    id_componente INTEGER NOT NULL,
    id_pedido INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    PRIMARY KEY (id_componente, id_pedido),
    FOREIGN KEY (id_componente) REFERENCES componente(id_componente) ON DELETE CASCADE,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE CASCADE
);

INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Admin', 'admin', 'admin@gmail.com', 'admin');
INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Douglas Baumgratz', 'doug', 'douglas@gmail.com', '123');
INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Jonas', 'jon', 'jonas@gmail.com', '123');
INSERT INTO usuario(nome_completo, nome_usuario, email, senha) VALUES ('Allan', 'all', 'allanmaralr@gmail.com', '123');

INSERT INTO promocao(codigo, desconto) VALUES ('COMPRAEMDOBRO', 0.5);
INSERT INTO promocao(codigo, desconto) VALUES ('DEZPORCENTO', 0.9);

INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (1, 1, 'X-Bacon', 'Pendente');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (2, 2, 'X-Salada', 'Em Produção');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (3, 1, 'Hamburguer Simples', 'Encaminhado');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (4, 2, 'X-Egg', 'Entregue');
INSERT INTO pedido(id_usuario, id_promocao, titulo, estado) VALUES (4, 1, 'X-egg', 'Cancelado');

INSERT INTO componente (e_produto, preco, titulo, descricao, id_pai) VALUES (TRUE , 34.0, 'Pizza Pan Grande Mussarela', 'A incomparável mussarela Pizza Hut, servida sobre molho de tomate especial.', NULL);
INSERT INTO componente (e_produto, preco, titulo, descricao, id_pai) VALUES (TRUE , 12.0, 'Monte seu cachorro-quente gourmet!', '', NULL);
INSERT INTO componente (e_produto, preco, titulo, descricao, id_pai) VALUES (FALSE, 1.50, 'Peperoni', '', 2);
INSERT INTO componente (e_produto, preco, titulo, descricao, id_pai) VALUES (FALSE, 1.50, 'Parmesão', '', 2);
INSERT INTO componente (e_produto, preco, titulo, descricao, id_pai) VALUES (FALSE, 1.50, 'Batata palha', '', 2);
INSERT INTO componente (e_produto, preco, titulo, descricao, id_pai) VALUES (TRUE , 18.9, 'Almoço Executivo', 'Refeição na embalagem com divisórias, salada embalagem a parte e suco natural de 500ml a sua escolha!', NULL);
