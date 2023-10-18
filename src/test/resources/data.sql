INSERT INTO `role`(`id`, `nome`) VALUES (1,'ROLE_USER');
INSERT INTO `role`(`id`, `nome`) VALUES (2,'ROLE_ADMIN');


INSERT INTO `jogadores`(`id`, `bio`, `data_nasc`, `email`, `horario_fim`, `horario_inicio`, `nome`, `senha`, `url_foto`) VALUES
    (1, 'Apenas uma Ana', '2000-01-01', 'ana@email.com', '00:00:00','22:00:00','Ana','Teste123', 'https://ana.png'),
    (2, 'Apenas uma Bia', '1990-10-20', 'bia@email.com', '00:02:00','00:00:00','Bia','Teste123', 'https://bia.png');

INSERT INTO `jogador_roles`(`id_jogador`, `id_role`) VALUES
    (1, 2),
    (2, 1);