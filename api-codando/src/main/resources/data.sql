INSERT INTO Educador (nome, sobrenome, email, senha)
VALUES ('nome1', 'sobrenome1', 'email@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC');

INSERT INTO escolaridade (descricao) VALUES ('6º Ano');
INSERT INTO escolaridade (descricao) VALUES ('7º Ano');
INSERT INTO escolaridade (descricao) VALUES ('8º Ano');
INSERT INTO escolaridade (descricao) VALUES ('9º Ano');
INSERT INTO Turma (nome, senha, id_escolaridade, id_educador, status_turma)
VALUES ('Turma 1', 'senha123', 1, 1, true);

INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido23', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido233', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2333', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2331', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido233111', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2336', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2337', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2338', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2339', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade,id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apel', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1, 1, 1);

INSERT INTO Turma (nome, senha, id_escolaridade, id_educador, status_turma)
VALUES ('Turma 2', 'senha1234', 1, 1, true);

INSERT INTO Modulo (nome)
VALUES ('Nome do Modulo');

INSERT INTO Tema (nome)
VALUES ('LP');

INSERT INTO Aula (titulo, descricao, nivel_dificuldade, pontuacao_maxima, tema_id_tema, turma_id_turma)
VALUES ('IF', 'Aprendendo a usar "IF"', 1000, 100, 1, 1);

INSERT INTO csv (ano_letivo, aba1, aba2, aba3, apr1, apr2, apr3, cd_escola, cd_rede_ensino, cd_tp_identificador, nm_completo_escola, nm_diretoria, nm_municipio, rep1, rep2, rep3) VALUES
(2021, 0, 0, 0, 0, 99.56, 0, 34344, true, 8, 'QUINZINHO CAMARGO PREFEITO', 'PIRAJU', 'PIRAJU', 0, 1, 0),
(2021, 0, 0, 0, 0, 100, 0, 34356, true, 8, 'NHONHO BRAGA CORONEL', 'PIRAJU', 'PIRAJU', 0, 2, 0),
(2021, 0, 0, 0, 0, 100, 0, 34459, true, 8, 'ATALIBA LEONEL', 'PIRAJU', 'PIRAJU', 0, 3, 0),
(2021, 0, 0, 0, 100, 0, 0, 17152, true, 8, 'MAURA ARRUDA GUIDOLIN PROFA', 'AMERICANA', 'AMERICANA', 0, 4, 0),
(2021, 0, 0, 0, 100, 0, 0, 17164, true, 8, 'JOAO DE CASTRO GONCALVES DR', 'AMERICANA', 'AMERICANA', 0, 5, 0),
(2021, 0, 0, 0, 94.74, 93.35, 0, 17176, true, 8, 'MAGI MONSENHOR', 'AMERICANA', 'AMERICANA', 5.26, 6.65, 0);

INSERT INTO Pergunta (texto, aula_id) VALUES
('Qual a resposta da vida, do universo e tudo mais?1', 1),
('Qual a resposta da vida, do universo e tudo mais?11', 1),
('Qual a resposta da vida, do universo e tudo mais?111', 1),
('Qual a resposta da vida, do universo e tudo mais?1111', 1),
('Qual a resposta da vida, do universo e tudo mais?11111', 1),
('Qual a resposta da vida, do universo e tudo mais?111111', 1);

INSERT INTO Resposta (texto, correta, pergunta_id) VALUES
('42', false, 3),
('42', false, 3),
('42', false, 3),
('42', false, 3),
('42', false, 3),

('42', false, 6),
('42', false, 6),
('42', false, 6),
('42', false, 6),
('42', false, 6);