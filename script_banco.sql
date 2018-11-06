DROP TABLE pedido;
DROP TABLE usuario;



CREATE TABLE usuario (
    id_usuario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome_completo VARCHAR(200) NOT NULL,
    nome_usuario VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(20) NOT NULL    
);

CREATE TABLE pedido (
    id_pedido INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_usuario INTEGER NOT NULL,
    id_promocao INTEGER,
    titulo VARCHAR(50) NOT NULL,
    estado VARCHAR(15) NOT NULL,    
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
    FOREIGN KEY (id_promocao) REFERENCES usuario(id_promocao) ON DELETE CASCADE
);

CREATE TABLE promocao (
	id_promocao INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    codigo VARCHAR(50) NOT NULL,
    desconto DECIMAL
);

INSERT INTO usuario(nome_completo, nome_usuario, email, senha) 
    VALUES ('Admin', 'admin', 'admin@gmail.com', 'admin');

INSERT INTO usuario(nome_completo, nome_usuario, email, senha) 
    VALUES ('Douglas Baumgratz', 'doug', 'douglas@gmail.com', '123');

INSERT INTO usuario(nome_completo, nome_usuario, email, senha) 
    VALUES ('Jonas', 'jon', 'jonas@gmail.com', '123');

INSERT INTO usuario(nome_completo, nome_usuario, email, senha) 
    VALUES ('Allan', 'all', 'allan@gmail.com', '123');


INSERT INTO promocao(codigo, desconto) 
	VALUES ('COMPRAEMDOBRO', 0.5)

INSERT INTO promocao(codigo, desconto) 
	VALUES ('DEZPORCENTO', 0.9	)


INSERT INTO pedido(id_usuario, titulo, descricao, estado, promocao) 
    VALUES (1, 'X-Bacon', 'Sem salada e sem maionese','Pendente', 'FRETE GRATIS');

INSERT INTO pedido(id_usuario, titulo, descricao, estado, promocao) 
    VALUES (2, 'X-Salada', 'Sem alface','Em Produção', 'COMPRA EM DOBRO');

INSERT INTO pedido(id_usuario, titulo, descricao, estado, promocao)  
    VALUES (3, 'Hamburguer Simples', 'Completo','Encaminhado', '');

INSERT INTO pedido(id_usuario, titulo, descricao, estado, promocao)  
    VALUES (4, 'X-Egg', 'Completo','Entregue', 'FRETE GRATIS');

INSERT INTO pedido(id_usuario, titulo, descricao, estado, promocao)  
    VALUES (4, 'X-egg', 'Completo','Cancelado', 'FRETE GRATIS');
