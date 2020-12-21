INSERT INTO supervisor (name) VALUES ('Bent');
INSERT INTO supervisor (name) VALUES ('Svend');
INSERT INTO supervisor (name) VALUES ('Lars Larsen');
INSERT INTO supervisor (name) VALUES ('Per Petersen');

INSERT INTO student (name, email) VALUES ('Frederik','fred@stud.kea.dk');
INSERT INTO student (name, email) VALUES ('Andreas','and@stud.kea.dk');

INSERT INTO students_supervisor (student_id, supervisor_id) values (1, 1);
INSERT INTO students_supervisor (student_id, supervisor_id) values (2, 2);
INSERT INTO students_supervisor (student_id, supervisor_id) values (1, 3);