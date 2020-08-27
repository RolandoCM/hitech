
INSERT INTO ROLEs (id, name ) VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLEs (id, name ) VALUES (2, 'ROLE_EMPLOYEE');

INSERT INTO EMPLOYEE(id, name, email, code, username, password, enabled) VALUES(1, 'admin', sss@email.com', 'admin', '$2a$10$0seZFMpDnKZLHRzWgR45je9OYFeKWGp1OFJLdVHrf\/.ijvFQJyBx2', true);

INSERT INTO EM_ROLES(1,1);
INSERT INTO EM_ROLES(2,2);
