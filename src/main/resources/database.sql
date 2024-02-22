CREATE DATABASE escuela;

CREATE TABLE estudiante(
    id int NOT NULL AUTO_INCREMENT,

    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(200) NOT NULL,
    promedio float,

    PRIMARY KEY (id)
);

CREATE TABLE materia(
    id int NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE estudiante_materia(
    id int NOT NULL AUTO_INCREMENT,

    estudiante_id int NOT NULL,
    materia_id int NOT NULL,
    calificacion int,

    PRIMARY KEY (id),

    CONSTRAINT FK_ESTUDIANTE FOREIGN KEY (estudiante_id)
    REFERENCES estudiante (id),

    CONSTRAINT FK_MATERIA FOREIGN KEY (materia_id)
    REFERENCES materia (id)
);

SELECT * FROM estudiante_materia AS em INNER JOIN materia AS m ON em.materia_id = m.id WHERE em.estudiante_id = 1;