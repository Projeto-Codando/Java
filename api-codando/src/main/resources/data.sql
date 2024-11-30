INSERT INTO Educador (nome, sobrenome, email, senha)
VALUES ('nome1', 'sobrenome1', 'email@email.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC');

INSERT INTO escolaridade (descricao)
VALUES ('6º Ano');
INSERT INTO escolaridade (descricao)
VALUES ('7º Ano');
INSERT INTO escolaridade (descricao)
VALUES ('8º Ano');
INSERT INTO escolaridade (descricao)
VALUES ('9º Ano');
INSERT INTO Turma (nome, senha, id_escolaridade, id_educador, status_turma)
VALUES ('Turma 1', 'senha123', 1, 1, true);

INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300,
        1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido23', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido233', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2333', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2331', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido233111', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2336', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2337', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2338', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apelido2339', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true',
        300, 1, 1, 1);
INSERT INTO Aluno (nome, sobrenome, apelido, senha, status, moedas, id_turma, id_escolaridade, id_avatar)
VALUES ('aluno2', 'sobrenome2', 'apel', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', 'true', 300, 1,
        1, 1);

INSERT INTO Turma (nome, senha, id_escolaridade, id_educador, status_turma)
VALUES ('Turma 2', 'senha1234', 1, 1, true);

INSERT INTO Modulo (nome)
VALUES ('Nome do Modulo');

INSERT INTO Tema (nome)
VALUES ('Condicional'),
       ('Laço de Repetição'),
       ('Variavel');

INSERT INTO Aula (titulo, descricao, nivel_dificuldade, pontuacao_maxima, tema_id_tema, turma_id_turma)
VALUES ('If / Else',
        'Aula detalhada sobre a combinação das estruturas condicionais if e else, incluindo exemplos de uso em fluxos de controle.',
        500, 50000, 1, 1),
       ('Switch Case',
        'Aula explicativa sobre a estrutura condicional switch case, ideal para selecionar entre várias opções baseadas em uma única variável.',
        500, 50000, 1, 1),
       ('Variável',
        'Uma variável é um espaço de memória identificado por um nome que armazena valores que podem ser alterados durante a execução do programa.',
        500, 50000, 2, 1);



INSERT INTO csv (ano_letivo, aba1, aba2, aba3, apr1, apr2, apr3, cd_escola, cd_rede_ensino, cd_tp_identificador,
                 nm_completo_escola, nm_diretoria, nm_municipio, rep1, rep2, rep3)
VALUES (2021, 0, 0, 0, 0, 99.56, 0, 34344, true, 8, 'QUINZINHO CAMARGO PREFEITO', 'PIRAJU', 'PIRAJU', 0, 1, 0),
       (2021, 0, 0, 0, 0, 100, 0, 34356, true, 8, 'NHONHO BRAGA CORONEL', 'PIRAJU', 'PIRAJU', 0, 2, 0),
       (2021, 0, 0, 0, 0, 100, 0, 34459, true, 8, 'ATALIBA LEONEL', 'PIRAJU', 'PIRAJU', 0, 3, 0),
       (2021, 0, 0, 0, 100, 0, 0, 17152, true, 8, 'MAURA ARRUDA GUIDOLIN PROFA', 'AMERICANA', 'AMERICANA', 0, 4, 0),
       (2021, 0, 0, 0, 100, 0, 0, 17164, true, 8, 'JOAO DE CASTRO GONCALVES DR', 'AMERICANA', 'AMERICANA', 0, 5, 0),
       (2021, 0, 0, 0, 94.74, 93.35, 0, 17176, true, 8, 'MAGI MONSENHOR', 'AMERICANA', 'AMERICANA', 5.26, 6.65, 0);

INSERT INTO Avatar (id, descricao, preco, imagemURL)
VALUES (1, 'Chimpaze', 0,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_default.png'),
       (2, 'Chimpaze Bombeiro', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_bombeiro.png'),
       (3, 'Chimpaze Pirata', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_pirata.png'),
       (4, 'Chimpaze Policial', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_policial.png'),
       (5, 'Chimpaze Touca', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/chimpaZe_touca.png'),
       (6, 'Preguiça', 350, 'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica.png'),
       (7, 'Preguiça Leal', 350,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_leal.png'),
       (8, 'Preguiça Ney', 350,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_ney.png'),
       (9, 'Preguiça Popo', 350,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_popo.png'),
       (10, 'Preguiça Senna', 350,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/preguica_senna.png'),
       (11, 'Cachorro', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_default.png'),
       (12, 'Cachorro Magico', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_cartola.png'),
       (13, 'Cachorro da Roça', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_chapeu.png'),
       (14, 'Cachorra FruFru', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_laco.png'),
       (15, 'Cachorro Touca', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/cachorro_touca.png'),
       (16, 'Gato', 150, 'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_default.png'),
       (17, 'Gato Aeromoça', 150,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_aeromoco.png'),
       (18, 'Gato Bruxa', 150,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_bruxa.png'),
       (19, 'Gato Marinheiro', 150,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_marinheiro.png'),
       (20, 'Gato Medico', 150,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/gato_medico.png'),
       (21, 'Urubu', 200, 'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_default.png'),
       (22, 'Urubu Potter', 200,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_frio.png'),
       (23, 'Urubu Judoca', 200,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_judoca.png'),
       (24, 'Urubu Praiano', 200,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_oculos.png'),
       (25, 'Urubu Vilão', 200,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/urubu_vilao.png'),
       (26, 'Zebra', 100, 'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_default.png'),
       (27, 'Zebra Cartola', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_cartola.png'),
       (28, 'Zebra Vaqueira', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_cowboy.png'),
       (29, 'Zebra Mexicana', 100,
        'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_mexicana.png'),
       (30, 'Zebra Rei', 100, 'https://qxztjedmqxjnfloewgbv.supabase.co/storage/v1/object/public/macaco/zebra_rei.png');

INSERT INTO Pergunta (texto, aula_id)
VALUES ('Em uma aventura na selva, um macaco curioso encontrou uma árvore mágica cheia de frutas diferentes. Para saber quais frutas ele ainda não experimentou, ele precisa comparar se as duas frutas são diferentes. Qual símbolo ele deve usar para fazer essa comparação?',
        1),
       ('Em uma aventura na floresta, um grupo de macacos encontra uma árvore carregada de bananas. Eles estão usando um código JavaScript para decidir o que fazer com as bananas: ',
        1),
       ('Você está ajudando um grupo de macacos programadores a desenvolver um sistema de acesso a uma caverna misteriosa na floresta. Para garantir a segurança, eles precisam verificar se a senha inserida pelo explorador tem pelo menos oito caracteres. Qual seria a melhor estrutura para realizar essa verificação? ',
        1),
       ('Em uma aventura noturna, os macacos precisam determinar se a lua está cheia para realizar um ritual especial. Eles têm um sensor que retorna o valor true se a lua estiver cheia e false caso contrário. Como os macacos podem usar uma estrutura de if para verificar se a lua está cheia e imprimir "A lua está cheia!"? ',
        1),
       ('Os macacos querem verificar se a temperatura está acima de 30 graus para decidir se vão nadar no rio. Eles possuem uma variável chamada temperatura. Qual estrutura de if é adequada para essa verificação?',
        1),
       ('Os macacos precisam verificar se a altura de uma árvore é maior que 15 metros para escolher a árvore certa para a competição. Eles possuem uma variável alturaArvore. Qual estrutura de if usariam?',
        1),
       ('Um macaco curioso está testando diferentes tipos de frutas para ver quais são comestíveis. Ele tem uma variável frutaComestivel que retorna true se a fruta for comestível e false caso contrário. Como ele pode usar uma estrutura de if para verificar se a fruta é comestível e imprimir "A fruta é comestível!"?',
        1),
       ('Na floresta, os macacos estão aprendendo a linguagem dos pássaros para se comunicarem melhor. Para entender os sons dos pássaros, eles precisam identificar se dois cantos são iguais. Qual símbolo eles usam para fazer essa comparação?',
        2),
       ('Os macacos estão classificando diferentes tipos de folhas que encontram na selva. Eles usam um código onde 1 representa folhas grandes, 2 representa folhas médias e 3 representa folhas pequenas. Como eles podem usar um switch case para imprimir o tipo de folha?',
        2),
       ('Durante uma celebração na floresta, os macacos querem decidir que tipo de dança fazer. Eles têm uma variável tipoDanca onde a representa a dança do fogo, b representa a dança da chuva, e c representa a dança do sol. Qual switch case eles usariam?',
        2),
       ('Os macacos estão escolhendo frutas para um banquete. Eles têm uma variável fruta onde 1 representa bananas, 2 representa maçãs, e 3 representa mangas. Qual switch case eles usariam para imprimir a fruta escolhida? ',
        2),
       ('Os macacos estão decidindo a cor das flores para decorar suas casas. Eles têm uma variável corFlor onde red representa flores vermelhas, blue representa flores azuis, e yellow representa flores amarelas. Como usariam um switch case para decidir a cor? ',
        2),
       ('Em um jogo de esportes na selva, os macacos precisam decidir qual jogo jogar baseado no valor da variável jogo, onde 1 representa futebol, 2 representa basquete, e 3 representa vôlei. Qual switch case eles usariam? ',
        2);

INSERT INTO Resposta (texto, correta, pergunta_id)
VALUES ('==', false, 1),
       ('!=', true, 1),
       ('!', false, 1),
       ('?', false, 1),

       ('A quantidade de bananas é menor que 5!', false, 2),
       ('A quantidade de bananas é igual a 5!', false, 2),
       ('A quantidade de bananas é diferente que 5', true, 2),
       ('Nenhuma das anteriores', false, 2),

       ('Iteração para contar caracteres', false, 3),
       ('Verificação do comprimento da string', true, 3),
       ('Loop para verificar repetidamente', false, 3),
       ('Avaliação da complexidade da senha', false, 3),

       ('luaCheia', true, 4),
       ('luaCheia == false', false, 4),
       ('luaCheia != true', false, 4),
       ('luaCheia =! false', false, 4),

       ('temperatura < 30', false, 5),
       ('temperatura >= 30', true, 5),
       ('temperatura <= 30', false, 5),
       ('temperatura != 30', false, 5),

       ('alturaArvore <= 15', false, 6),
       ('alturaArvore < 15', false, 6),
       ('alturaArvore > 15', true, 6),
       ('alturaArvore == 15', false, 6),

       ('frutaComestivel != true', false, 7),
       ('frutaComestivel == false', false, 7),
       ('frutaComestivel == true', true, 7),
       ('frutaComestivel != false', false, 7),

       ('=', false, 8),
       ('-', false, 8),
       ('==', true, 8),
       ('*', false, 8),

       ('Folha grande', false, 9),
       ('Folha média', true, 9),
       ('Folha pequena', false, 9),
       ('Tipo de folha desconhecido', false, 9),

       ('Dança do fogo', false, 10),
       ('Dança da chuva', false, 10),
       ('Dança do sol', true, 10),
       ('Tipo de dança desconhecido', false, 10),

       ('Bananas', true, 11),
       ('Maçãs', false, 11),
       ('Mangas', false, 11),
       ('Fruta desconhecida', false, 11),

       ('temperatura < 30', false, 12),
       ('temperatura >= 30', true, 12),
       ('temperatura == 30', false, 12),
       ('temperatura != 30', false, 12),

       ('Futebol', false, 13),
       ('Basquete', false, 13),
       ('Vôlei', true, 13),
       ('Jogo desconhecido', false, 13);
