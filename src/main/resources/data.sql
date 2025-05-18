INSERT INTO role (name) VALUES ('CUSTOMER');
INSERT INTO role (name) VALUES ('ADMINISTRATOR');

INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');
INSERT INTO module (name, base_path) VALUES ('PERMISSION', '/permissions');

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS','', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','', 'POST', true, 1);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('LOGOUT','/logout','POST', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', false, 2);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PERMISSIONS','','GET', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERMISSION','/[0-9]*','GET', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERMISSION','','POST', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_ONE_PERMISSION','/[0-9]*','DELETE', false, 3);

INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 6);

INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 6);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 7);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 8);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 10);

-- dev credentials
-- user: asalazarj
-- password : Temporal@32
INSERT INTO users (username, name, password, role_id) VALUES ('asalazarj', 'Armando Salazar', '$2a$10$Pp1QKuLURYKH/ijzicVR8upDeTNECCFeiXERlODJBqDUVv0DT6Fsi', 2);
