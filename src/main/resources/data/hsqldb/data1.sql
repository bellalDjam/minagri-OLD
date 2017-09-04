/*
 * postgreSQL DB script.
 * Load the database with reference data and unit test data.
 */
-- password is 'password'
INSERT INTO Account (id, reference_id, username,
 password, enabled, credential_expired, expired, 
 locked, version, created_By, created_At, updated_By,
  updated_at) VALUES (1,'a07bd221-3ecd-4893-a0f0-78d7c0fbf94e', 'user', '$2a$10$9/44Rne7kQqPXa0cY6NfG.3XzScMrCxFYjapoLq/wFmHz7EC9praK', true, false, false, false, 0, 'user', NOW(), NULL, NULL);
-- password is 'operations'
﻿INSERT INTO Account (id, reference_id, username,
 password, enabled, credential_expired, expired, 
 locked, version, created_By, created_At, updated_By,
  updated_at) VALUES (2,'7bd137c8-ab64-4a45-bf2d-d9bae3574622', 'operations', '$2a$10$CoMVfutnv1qZ.fNlHY1Na.rteiJhsDF0jB1o.76qXcfdWN6As27Zm', true, false, false, false, 0, 'user', NOW(), NULL, NULL);
  
  INSERT INTO Role (id, code, label, ordinal, effective_At, expires_At, created_At) VALUES (1, 'ROLE_USER', 'User', 0, '2015-01-01 00:00:00', NULL, NOW());
INSERT INTO Role (id, code, label, ordinal, effective_At, expires_At, created_At) VALUES (2, 'ROLE_ADMIN', 'Admin', 1, '2015-01-01 00:00:00', NULL, NOW());
INSERT INTO Role (id, code, label, ordinal, effective_At, expires_At, created_At) VALUES (3, 'ROLE_SYSADMIN', 'System Admin', 2, '2015-01-01 00:00:00', NULL, NOW());

INSERT INTO AccountRole (account_Id, role_Id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'user' and r.id = 1;
INSERT INTO AccountRole (account_Id, role_Id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'operations' and r.id = 3;