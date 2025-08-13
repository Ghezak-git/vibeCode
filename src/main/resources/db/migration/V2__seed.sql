-- V2__seed.sql
INSERT INTO mst_akses (nama_akses) VALUES ('ADMIN'), ('STAFF'), ('VIEWER');

INSERT INTO mst_menu (nama_menu, path) VALUES
  ('DASHBOARD', '/dashboard'),
  ('USERS', '/users'),
  ('ROLES', '/roles'),
  ('MENUS', '/menus'),
  ('CATEGORIES', '/categories'),
  ('PRODUCTS', '/products'),
  ('SUPPLIERS', '/suppliers'),
  ('LOGS', '/logs');

-- ADMIN akses ke semua menu
INSERT INTO map_akses_menu (id_akses, id_menu)
SELECT a.id, m.id FROM mst_akses a, mst_menu m WHERE a.nama_akses = 'ADMIN';

-- STAFF akses ke subset menu
INSERT INTO map_akses_menu (id_akses, id_menu)
SELECT a.id, m.id FROM mst_akses a, mst_menu m WHERE a.nama_akses = 'STAFF' AND m.nama_menu IN ('DASHBOARD','CATEGORIES','PRODUCTS','SUPPLIERS','LOGS');

-- VIEWER akses read-only
INSERT INTO map_akses_menu (id_akses, id_menu)
SELECT a.id, m.id FROM mst_akses a, mst_menu m WHERE a.nama_akses = 'VIEWER' AND m.nama_menu IN ('DASHBOARD','CATEGORIES','PRODUCTS','SUPPLIERS','LOGS');

-- User admin (password: admin, bcrypt hash)
INSERT INTO mst_user (username, password, email, nama_lengkap, is_registered, id_akses)
VALUES ('admin', '$2a$10$7QJ8Qw6Qw6Qw6Qw6Qw6QwOQw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6Qw6', 'admin@example.com', 'Administrator', true, (SELECT id FROM mst_akses WHERE nama_akses='ADMIN'));
