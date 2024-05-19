INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas)
VALUES ('aluno1', 'sobrenome1', 'apelido1', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'status1', 51);

INSERT INTO aluno (apelido, moedas, nome, senha, sobrenome, status)
VALUES ('Jojo', 100, 'João', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'Silva', 'ativo');

INSERT INTO aluno (apelido, moedas, nome, senha, sobrenome, status)
VALUES ('Mari', 200, 'Maria', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'Santos', 'ativo');

INSERT INTO aluno (apelido, moedas, nome, senha, sobrenome, status)
VALUES ('Carl', 150, 'Carlos', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'Pereira', 'ativo');

INSERT INTO aluno (apelido, moedas, nome, senha, sobrenome, status)
VALUES ('usuarioTeste', 300, 'Ana', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'Silva', 'ativo');

INSERT INTO Educador (nome, sobrenome, email, senha)
VALUES ('nome1', 'sobrenome1', 'email@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC');

INSERT INTO escolaridade (descricao) VALUES ('Ensino Fundamental');

INSERT INTO Turma (nome, senha, fk_escolaridade_id_escolaridade, fk_educador_id_educador, status_turma)
VALUES ('Turma 1', 'senha123', 1, 1, true);

INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, turma_id_turma)
VALUES ('aluno2', 'sobrenome2', 'apelido2', 'senha123', 'ativo', 50, 1);

INSERT INTO csv (ano_letivo, aba1, aba2, aba3, apr1, apr2, apr3, cd_escola, cd_rede_ensino, cd_tp_identificador, nm_completo_escola, nm_diretoria, nm_municipio, rep1, rep2, rep3) VALUES
(2021, 0, 0, 0, 0, 99.56, 0, 34344, true, 8, 'QUINZINHO CAMARGO PREFEITO', 'PIRAJU', 'PIRAJU', 0, 1, 0),
(2021, 0, 0, 0, 0, 100, 0, 34356, true, 8, 'NHONHO BRAGA CORONEL', 'PIRAJU', 'PIRAJU', 0, 2, 0),
(2021, 0, 0, 0, 0, 100, 0, 34459, true, 8, 'ATALIBA LEONEL', 'PIRAJU', 'PIRAJU', 0, 3, 0),
(2021, 0, 0, 0, 100, 0, 0, 17152, true, 8, 'MAURA ARRUDA GUIDOLIN PROFA', 'AMERICANA', 'AMERICANA', 0, 4, 0),
(2021, 0, 0, 0, 100, 0, 0, 17164, true, 8, 'JOAO DE CASTRO GONCALVES DR', 'AMERICANA', 'AMERICANA', 0, 5, 0),
(2021, 0, 0, 0, 94.74, 93.35, 0, 17176, true, 8, 'MAGI MONSENHOR', 'AMERICANA', 'AMERICANA', 5.26, 6.65, 0);