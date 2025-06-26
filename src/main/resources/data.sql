INSERT INTO status(name) VALUES ('ENABLED'), ('DISABLED'), ('PENDING');

INSERT INTO roles (name, status_id) VALUES ('CUSTOMER', 1), ('ADMINISTRATOR', 1);

INSERT INTO modules (name, base_path, status_id) VALUES ('CUSTOMER', '/customers', 1),
('AUTH', '/auth', 1), ('PERMISSION', '/permissions', 1), ('STATUS', '/status', 1),
('MODULES', '/modules', 1), ('ROLES', '/roles', 1), ('OPERATIONS', '/operations', 1);

INSERT INTO operations (name, path, http_method, permit_all, module_id, status_id) VALUES
('READ_ALL_CUSTOMERS','', 'GET', false, 1, 1), ('REGISTER','', 'POST', true, 1, 1),

('AUTHENTICATE','/authenticate', 'POST', true, 2, 1), ('VALIDATE-TOKEN','/validate-token', 'GET', true, 2, 1),
('LOGOUT','/logout','POST', true, 2, 1), ('READ_MY_PROFILE','/profile','GET', false, 2, 1),

('READ_ALL_PERMISSION','','GET', false, 3, 1),
('READ_ONE_PERMISSION','/[0-9]*','GET', false, 3, 1),
('CREATE_ONE_PERMISSION','','POST', false, 3, 1),
('UPDATE_ONE_PERMISSION','/[0-9]*','PUT', false, 3, 1),
('DELETE_ONE_PERMISSION','/[0-9]*','DELETE', false, 3, 1),

('READ_ALL_STATUS','','GET', false, 4, 1),
('READ_ONE_STATUS', '/[0-9]*','GET', false, 4, 1),
('CREATE_ONE_STATUS','','POST', false, 4, 1),
('UPDATE_ONE_STATUS','/[0-9]*','PUT', false, 4, 1),

('READ_ALL_MODULES','','GET', false, 5, 1),
('READ_ONE_MODULE', '/[0-9]*','GET', false, 5, 1),
('CREATE_ONE_MODULE','','POST', false, 5, 1),
('UPDATE_ONE_MODULE','/[0-9]*','PUT', false, 5, 1),
('ENABLED_MODULE','/[0-9]*/enabled','PUT', false, 5, 1),
('DISABLED_MODULE','/[0-9]*/disabled','PUT', false, 5, 1),
('FILTER_MODULE','/filter','GET', false, 5, 1),

('READ_ALL_ROLES','','GET', false, 6, 1),
('READ_ONE_ROLE', '/[0-9]*','GET', false, 6, 1),
('CREATE_ONE_ROLE','','POST', false, 6, 1),
('UPDATE_ONE_ROLE','/[0-9]*','PUT', false, 6, 1),
('ENABLED_ROLE','/[0-9]*/enabled','PUT', false, 6, 1),
('DISABLED_ROLE','/[0-9]*/disabled','PUT', false, 6, 1),
('FILTER_ROLE','/filter','GET', false, 6, 1),

('READ_ALL_OPERATIONS','','GET', false, 7, 1),
('READ_ONE_OPERATION', '/[0-9]*','GET', false, 7, 1),
('CREATE_ONE_OPERATION','','POST', false, 7, 1),
('UPDATE_ONE_OPERATION','/[0-9]*','PUT', false, 7, 1),
('ENABLED_OPERATION','/[0-9]*/enabled','PUT', false, 7, 1),
('DISABLED_OPERATION','/[0-9]*/disabled','PUT', false, 7, 1),
('FILTER_OPERATIONS','/filter','GET', false, 7, 1);


INSERT INTO granted_permission (role_id, operation_id) VALUES
(1, 6),
(2, 1), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (2, 11), (2, 12), (2, 13), (2, 14), (2, 15),
(2, 16),(2, 17),(2, 18),(2, 19), (2, 20),(2, 21),(2, 22),(2, 23), (2, 24), (2, 25), (2, 26),
(2, 27), (2, 28), (2, 29) , (2, 30), (2, 31), (2, 32), (2, 33), (2, 34), (2, 35), (2, 36);


-- dev credentials
-- user: asalazarj
-- password : Temporal@32
INSERT INTO users (username, email, name, password, role_id, status_id) VALUES ('asalazarj', 'armando@gmail.com', 'Armando Salazar', '$2a$10$Pp1QKuLURYKH/ijzicVR8upDeTNECCFeiXERlODJBqDUVv0DT6Fsi', 2, 1);
