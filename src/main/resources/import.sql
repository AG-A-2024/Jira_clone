-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequenceNr myentity_seq restart with 4;

-- Users
INSERT INTO User(email, indexNr, lastName, name, password)
VALUES ('mbar@pbs.edu.pl', '116980','Bar', 'M', '$2a$12$DHFo.CDNVd.KVo9r7XlJCO1qVWjb2JjJvuX8Oi5TGIDCLuZiq2kHy'); -- pass: ochuj

INSERT INTO User(email, indexNr, lastName, name, password)
VALUES ('aaa@pbs.edu.pl', '116980','Bar', 'M', '$2a$12$DHFo.CDNVd.KVo9r7XlJCO1qVWjb2JjJvuX8Oi5TGIDCLuZiq2kHy'); -- pass: ochuj

INSERT INTO User(email, indexNr, lastName, name, password)
VALUES ('admin@pbs.edu.pl', '','Bar', 'M', '$2a$12$DHFo.CDNVd.KVo9r7XlJCO1qVWjb2JjJvuX8Oi5TGIDCLuZiq2kHy'); -- pass: ochuj

INSERT INTO user_roles (id, roles)
VALUES (1, 'USER');

INSERT INTO user_roles (id, roles)
VALUES (2, 'USER');

INSERT INTO user_roles (id, roles)
VALUES (3, 'ADMIN');

-- Projekty
INSERT INTO Project (projectName, projectDescription, creationTime, releaseDate, projectOwner_id)
VALUES ('Projekt 1', 'Opis Projektu 1', '2024-05-13T00:00:00', '2024-06-01T00:00:00', 1);

INSERT INTO Project (projectName, projectDescription, creationTime, releaseDate, projectOwner_id)
VALUES ('Projekt 2', 'Opis Projektu 2', '2024-05-14T00:00:00', '2024-06-02T00:00:00', 1);

INSERT INTO Project (projectName, projectDescription, creationTime, releaseDate, projectOwner_id)
VALUES ('Projekt 3', 'Opis Projektu 3', '2024-05-15T00:00:00', '2024-06-03T00:00:00', 1);

INSERT INTO Project (projectName, projectDescription, creationTime, releaseDate, projectOwner_id)
VALUES ('Projekt 4', 'Opis Projektu 4', '2024-05-16T00:00:00', '2024-06-04T00:00:00', 1);

INSERT INTO Project (projectName, projectDescription, creationTime, releaseDate, projectOwner_id)
VALUES ('Projekt 5', 'Opis Projektu 5', '2024-05-17T00:00:00', '2024-06-05T00:00:00', 1);

-- inserty dla taskow
INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 1', 1, 'Opis Zadania 1', '2024-05-20T00:00:00', 1);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 2', 2, 'Opis Zadania 2', '2024-05-21T00:00:00', 1);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 3', 3, 'Opis Zadania 3', '2024-05-22T00:00:00', 1);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 4', 4, 'Opis Zadania 4', '2024-05-23T00:00:00', 1);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 5', 5, 'Opis Zadania 5', '2024-05-24T00:00:00', 1);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 6', 1, 'Opis Zadania 6', '2024-05-25T00:00:00', 2);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 7', 2, 'Opis Zadania 7', '2024-05-26T00:00:00', 2);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 8', 3, 'Opis Zadania 8', '2024-05-27T00:00:00', 2);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 9', 4, 'Opis Zadania 9', '2024-05-28T00:00:00', 2);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 10', 5, 'Opis Zadania 10', '2024-05-29T00:00:00', 2);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 11', 1, 'Opis Zadania 11', '2024-05-30T00:00:00', 3);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 12', 2, 'Opis Zadania 12', '2024-05-31T00:00:00', 3);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 13', 3, 'Opis Zadania 13', '2024-06-01T00:00:00', 3);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 14', 4, 'Opis Zadania 14', '2024-06-02T00:00:00', 3);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 15', 5, 'Opis Zadania 15', '2024-06-03T00:00:00', 3);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 16', 1, 'Opis Zadania 16', '2024-06-04T00:00:00', 4);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 17', 2, 'Opis Zadania 17', '2024-06-05T00:00:00', 4);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 18', 3, 'Opis Zadania 18', '2024-06-06T00:00:00', 4);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 19', 4, 'Opis Zadania 19', '2024-06-07T00:00:00', 4);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 20', 5, 'Opis Zadania 20', '2024-06-08T00:00:00', 4);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 21', 1, 'Opis Zadania 21', '2024-06-09T00:00:00', 5);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 22', 2, 'Opis Zadania 22', '2024-06-10T00:00:00', 5);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 23', 3, 'Opis Zadania 23', '2024-06-11T00:00:00', 5);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 24', 4, 'Opis Zadania 24', '2024-06-12T00:00:00', 5);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 25', 5, 'Opis Zadania 25', '2024-06-13T00:00:00', 5);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 26', 6, 'Opis Zadania 26', '2024-06-14T00:00:00', 1);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 27', 6, 'Opis Zadania 27', '2024-06-15T00:00:00', 2);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 28', 6, 'Opis Zadania 28', '2024-06-16T00:00:00', 3);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 29', 6, 'Opis Zadania 29', '2024-06-17T00:00:00', 4);

INSERT INTO Task (taskName, sequenceNr, description, creationDateTime, project_id)
VALUES ('Zadanie 30', 6, 'Opis Zadania 30', '2024-06-18T00:00:00', 5);

