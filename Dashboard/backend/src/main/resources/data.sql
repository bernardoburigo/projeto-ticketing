-- Tipo ingresso
INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 1, 'Show', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 1);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 2, 'Palestra', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 2);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 3, 'WorkShop', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 3);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 4, 'Teatro', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 4);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 5, 'Festival', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 5);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 6, 'Esportivo', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 6);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 7, 'NetWorking', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 7);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 8, 'Beneficente', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 8);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 9, 'Congresso', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 9);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 10, 'Feira', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 10);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 11, 'Exposição', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 11);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 12, 'Treinamento', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 12);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 13, 'Conveção', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 13);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 14, 'Reunião', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 14);

INSERT INTO categorias_eventos (i_categoria_evento, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 15, 'Musical', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM categorias_eventos WHERE i_categoria_evento = 15);


-- Roles
INSERT INTO roles (i_role, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 1, 'Admin', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE i_role = 1);

INSERT INTO roles (i_role, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 2, 'Usuário', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE i_role = 2);

INSERT INTO roles (i_role, nome, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 3, 'Organizador', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE i_role = 3);


-- Tipo ingresso
INSERT INTO tipos_ingressos (i_tipo_ingresso, nome, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 1, 'Vip', NULL, TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM tipos_ingressos WHERE i_tipo_ingresso = 1);

INSERT INTO tipos_ingressos (i_tipo_ingresso, nome, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 2, 'Pista', NULL, TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM tipos_ingressos WHERE i_tipo_ingresso = 2);

INSERT INTO tipos_ingressos (i_tipo_ingresso, nome, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 3, 'Meia entrada', NULL, TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM tipos_ingressos WHERE i_tipo_ingresso = 3);

INSERT INTO tipos_ingressos (i_tipo_ingresso, nome, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 4, 'Inteira', NULL, TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM tipos_ingressos WHERE i_tipo_ingresso = 4);

INSERT INTO tipos_ingressos (i_tipo_ingresso, nome, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 5, 'Especial', NULL, TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM tipos_ingressos WHERE i_tipo_ingresso = 5);


-- Permissoes
INSERT INTO permissoes (i_permissao, permissao, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 1, 'DEFAULT', 'Permissão padrão do sistema', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM permissoes WHERE permissao = 'DEFAULT');

INSERT INTO permissoes (i_permissao, permissao, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 2, 'ADMIN', 'Permissão de administrador com acesso total', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM permissoes WHERE permissao = 'ADMIN');

INSERT INTO permissoes (i_permissao, permissao, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 3, 'USUARIO', 'Permissão para usuários comuns', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM permissoes WHERE permissao = 'USUARIO');

INSERT INTO permissoes (i_permissao, permissao, descricao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 4, 'ORGANIZADOR', 'Permissão para organizadores de eventos', TRUE, FALSE, 1, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM permissoes WHERE permissao = 'ORGANIZADOR');



-- Admin -> ADMIN
INSERT INTO roles_permissoes (i_roles_permissoes, i_role, i_permissao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 1, r.i_role, p.i_permissao, TRUE, FALSE, 1, NOW(), NOW()
FROM roles r
JOIN permissoes p ON p.permissao = 'ADMIN'
WHERE r.i_role = 1
  AND NOT EXISTS (
    SELECT 1 FROM roles_permissoes rp WHERE rp.i_role = r.i_role AND rp.i_permissao = p.i_permissao
  );

-- Admin -> DEFAULT
INSERT INTO roles_permissoes (i_roles_permissoes, i_role, i_permissao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 2, r.i_role, p.i_permissao, TRUE, FALSE, 1, NOW(), NOW()
FROM roles r
JOIN permissoes p ON p.permissao = 'DEFAULT'
WHERE r.i_role = 1
  AND NOT EXISTS (
    SELECT 1 FROM roles_permissoes rp WHERE rp.i_role = r.i_role AND rp.i_permissao = p.i_permissao
  );

-- Usuário -> USUARIO
INSERT INTO roles_permissoes (i_roles_permissoes, i_role, i_permissao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 3, r.i_role, p.i_permissao, TRUE, FALSE, 1, NOW(), NOW()
FROM roles r
JOIN permissoes p ON p.permissao = 'USUARIO'
WHERE r.i_role = 2
  AND NOT EXISTS (
    SELECT 1 FROM roles_permissoes rp WHERE rp.i_role = r.i_role AND rp.i_permissao = p.i_permissao
  );

-- Usuário -> DEFAULT
INSERT INTO roles_permissoes (i_roles_permissoes, i_role, i_permissao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 4, r.i_role, p.i_permissao, TRUE, FALSE, 1, NOW(), NOW()
FROM roles r
JOIN permissoes p ON p.permissao = 'DEFAULT'
WHERE r.i_role = 2
  AND NOT EXISTS (
    SELECT 1 FROM roles_permissoes rp WHERE rp.i_role = r.i_role AND rp.i_permissao = p.i_permissao
  );

-- Organizador -> ORGANIZADOR
INSERT INTO roles_permissoes (i_roles_permissoes, i_role, i_permissao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 5, r.i_role, p.i_permissao, TRUE, FALSE, 1, NOW(), NOW()
FROM roles r
JOIN permissoes p ON p.permissao = 'ORGANIZADOR'
WHERE r.i_role = 3
  AND NOT EXISTS (
    SELECT 1 FROM roles_permissoes rp WHERE rp.i_role = r.i_role AND rp.i_permissao = p.i_permissao
  );

-- Organizador -> DEFAULT
INSERT INTO roles_permissoes (i_roles_permissoes, i_role, i_permissao, ativo, excluido, versao, aud_criado_data, aud_modificado_data)
SELECT 6, r.i_role, p.i_permissao, TRUE, FALSE, 1, NOW(), NOW()
FROM roles r
JOIN permissoes p ON p.permissao = 'DEFAULT'
WHERE r.i_role = 3
  AND NOT EXISTS (
    SELECT 1 FROM roles_permissoes rp WHERE rp.i_role = r.i_role AND rp.i_permissao = p.i_permissao
  );
