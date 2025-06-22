INSERT INTO status(name) VALUES ('ENABLED'), ('DISABLED'), ('PENDING');

INSERT INTO roles (name, status_id) VALUES ('CUSTOMER', 1), ('ADMINISTRATOR', 1);

INSERT INTO modules (name, base_path, status_id) VALUES ('CUSTOMER', '/customers', 1),
('AUTH', '/auth', 1), ('PERMISSION', '/permissions', 1), ('STATUS', '/status', 1),
('MODULES', '/modules', 1), ('ROLES', '/roles', 1);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES
('READ_ALL_CUSTOMERS','', 'GET', false, 1), ('REGISTER','', 'POST', true, 1),

('AUTHENTICATE','/authenticate', 'POST', true, 2), ('VALIDATE-TOKEN','/validate-token', 'GET', true, 2),
('LOGOUT','/logout','POST', true, 2), ('READ_MY_PROFILE','/profile','GET', false, 2),

('READ_ALL_PERMISSION','','GET', false, 3),
('READ_ONE_PERMISSION','/[0-9]*','GET', false, 3),
('CREATE_ONE_PERMISSION','','POST', false, 3),
('UPDATE_ONE_PERMISSION','/[0-9]*','PUT', false, 3),
('DELETE_ONE_PERMISSION','/[0-9]*','DELETE', false, 3),

('READ_ALL_STATUS','','GET', false, 4),
('READ_ONE_STATUS', '/[0-9]*','GET', false, 4),
('CREATE_ONE_STATUS','','POST', false, 4),
('UPDATE_ONE_STATUS','/[0-9]*','PUT', false, 4),

('READ_ALL_MODULES','','GET', false, 5),
('READ_ONE_MODULE', '/[0-9]*','GET', false, 5),
('CREATE_ONE_MODULE','','POST', false, 5),
('UPDATE_ONE_MODULE','/[0-9]*','PUT', false, 5),
('ENABLED_MODULE','/[0-9]*/enabled','PUT', false, 5),
('DISABLED_MODULE','/[0-9]*/disabled','PUT', false, 5),
('FILTER_MODULE','/filter','GET', false, 5),

('READ_ALL_ROLES','','GET', false, 6),
('READ_ONE_ROLE', '/[0-9]*','GET', false, 6),
('CREATE_ONE_ROLE','','POST', false, 6),
('UPDATE_ONE_ROLE','/[0-9]*','PUT', false, 6),
('ENABLED_ROLE','/[0-9]*/enabled','PUT', false, 6),
('DISABLED_ROLE','/[0-9]*/disabled','PUT', false, 6),
('FILTER_ROLE','/filter','GET', false, 6);


INSERT INTO granted_permission (role_id, operation_id) VALUES
(1, 6),
(2, 1), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (2, 11), (2, 12), (2, 13), (2, 14), (2, 15),
(2, 16),(2, 17),(2, 18),(2, 19), (2, 20),(2, 21),(2, 22),(2, 23), (2, 24), (2, 25), (2, 26),
(2, 27), (2, 28), (2, 29);


-- dev credentials
-- user: asalazarj
-- password : Temporal@32
INSERT INTO users (username, email, name, password, role_id) VALUES ('asalazarj', 'armando@gmail.com', 'Armando Salazar', '$2a$10$Pp1QKuLURYKH/ijzicVR8upDeTNECCFeiXERlODJBqDUVv0DT6Fsi', 2);
