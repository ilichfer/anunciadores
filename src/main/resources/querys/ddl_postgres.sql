-- u217299043_desarrollo.actividades

CREATE TABLE actividades (
  id int NOT NULL,
  nombre varchar(250) NOT NULL,
  fecha varchar(250) NOT NULL,
  cantidad_mesas int NOT NULL,
  PRIMARY KEY (id)
) ;


-- u217299043_desarrollo.comentario definition

CREATE TABLE comentario (
  id int NOT NULL,
  id_curso int NOT NULL,
  id_persona int NOT NULL,
  fecha varchar(250) NOT NULL,
  comentario varchar(1000) NOT NULL,
  PRIMARY KEY (id)
) ;


-- u217299043_desarrollo.consolidacion definition

CREATE TABLE consolidacion (
  id int NOT NULL,
  id_padre_espiritual int NOT NULL,
  id_persona_consolidar int NOT NULL,
  PRIMARY KEY (id)
) ;

-- u217299043_desarrollo.curso definition

CREATE TABLE curso (
  id int NOT NULL,
  fechafin varchar(255) DEFAULT NULL,
  fechainicio varchar(255) DEFAULT NULL,
  idpago int DEFAULT NULL,
  nombrecurso varchar(255) DEFAULT NULL,
  valortotal int DEFAULT NULL,
  comentario smallint NOT NULL,
  activo smallint NOT NULL,
  PRIMARY KEY (id)
) ;

-- u217299043_desarrollo.inscripcion_actividades definition

CREATE TABLE inscripcion_actividades (
  id int NOT NULL,
  id_persona int NOT NULL,
  id_actividad int NOT NULL,
  PRIMARY KEY (id)
) ;

-- u217299043_desarrollo.inscripciones definition

CREATE TABLE inscripciones (
  id int NOT NULL,
  id_curso int DEFAULT NULL,
  id_persona int DEFAULT NULL,
  PRIMARY KEY (id)
) ;


-- u217299043_desarrollo.mesa definition

CREATE TABLE mesa (
  id int NOT NULL,
  id_actividad int NOT NULL,
  nombre_mesa varchar(250) NOT NULL,
  PRIMARY KEY (id)
) ;

-- u217299043_desarrollo.notas definition

CREATE TABLE notas (
  id int NOT NULL,
  nomactividad varchar(255) DEFAULT NULL,
  idcurso date DEFAULT NULL,
  idpersonafk int DEFAULT NULL,
  nota int DEFAULT NULL,
  PRIMARY KEY (id)
) ;

-- u217299043_desarrollo.pago definition

CREATE TABLE pago (
  id int NOT NULL,
  fecha_pago varchar(26) DEFAULT NULL,
  id_curso int DEFAULT NULL,
  idpersona int DEFAULT NULL,
  valor int DEFAULT NULL,
  PRIMARY KEY (id)
) ;

CREATE TABLE parametrica (
  id int NOT NULL,
  id_parametros int NOT NULL,
  descripcion_parametro varchar(1000) NOT NULL,
  PRIMARY KEY (id)
);

-- u217299043_desarrollo.persona definition

CREATE TABLE persona (
  id int NOT NULL,
  apellido varchar(255) DEFAULT NULL,
  documento int DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  fechanacimiento varchar(255) DEFAULT NULL,
  nombre varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  telefono varchar(255) DEFAULT NULL,
  tipodocumento varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ;

-- u217299043_desarrollo.persona_mesa definition

CREATE TABLE persona_mesa (
  id int NOT NULL,
  id_mesa int NOT NULL,
  id_persona int NOT NULL,
  PRIMARY KEY (id)
) ;

-- u217299043_desarrollo.persona_rol definition

CREATE TABLE persona_rol (
  id int NOT NULL,
  id_persona int NOT NULL,
  id_rol int NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT personaid FOREIGN KEY (id_persona) REFERENCES persona (id),
  CONSTRAINT personarol FOREIGN KEY (id_rol) REFERENCES rol (id)
) ;


-- u217299043_desarrollo.rol definition

CREATE TABLE rol (
  id int NOT NULL,
  descripcion_rol varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ;


-- u217299043_desarrollo.usuario definition

CREATE TABLE usuario (
  idusuario int NOT NULL ,
  username varchar(250) NOT NULL,
  password varchar(250) NOT NULL,
  PRIMARY KEY (idusuario)
) ;






-- create table TDC

CREATE TABLE TDC (
  id int NOT NULL,
  id_persona int NOT NULL,
  dios varchar(1000) NOT NULL,
  dios_check smallint NOT NULL,
  mandato varchar(1000) NOT NULL,
  mandato_check smallint NOT NULL,
  promesa varchar(1000) NOT NULL,
  promesa_check smallint NOT NULL,
  mensaje varchar(1000) NOT NULL,
  mensaje_check smallint NOT NULL,
  aplicacion varchar(1000) NOT NULL,
  aplicacion_check smallint NOT NULL,
  fecha_registro varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE SEQUENCE sec_persona START 1;
CREATE SEQUENCE sec_curso START 1;
CREATE SEQUENCE sec_inscripcion START 1;
