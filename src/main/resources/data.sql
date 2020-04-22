--INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '123456');
--INSERT INTO USUARIO(nome, email, senha) VALUES('Pedro', 'pedro@gmail.com', '123456');

--INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
--INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');
--INSERT INTO CURSO(nome, categoria) VALUES('Java', 'Back-end');
--INSERT INTO CURSO(nome, categoria) VALUES('Angular', 'Front-end');

--INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
--INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
--INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);

INSERT INTO CATEGORIA(nome) VALUES ('Luz');
INSERT INTO CATEGORIA(nome) VALUES ('Agua');
INSERT INTO CATEGORIA(nome) VALUES ('Telefone');
INSERT INTO CATEGORIA(nome) VALUES ('Aluguel');
INSERT INTO CATEGORIA(nome) VALUES ('Gas');

INSERT INTO Fornecedor(nome) VALUES ('CEB');
INSERT INTO Fornecedor(nome) VALUES ('CAESB');
INSERT INTO Fornecedor(nome) VALUES ('Vivo');
INSERT INTO Fornecedor(nome) VALUES ('Gas Braz');
INSERT INTO Fornecedor(nome) VALUES ('Adega');

INSERT INTO Boleto(nome,dt_criacao,categoria_id,usuario_id,fornecedor_id) VALUES ('Energia Eletrica', '2020-03-23','1','1','1');
INSERT INTO Boleto(nome,dt_criacao,categoria_id,usuario_id,fornecedor_id) VALUES ('Agua', '2020-03-24','2','2','2');
INSERT INTO Boleto(nome,dt_criacao,categoria_id,usuario_id,fornecedor_id) VALUES ('Telefone e internet', '2020-03-25','1','3','3');