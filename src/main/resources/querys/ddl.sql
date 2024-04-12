ALTER TABLE inscripcion_actividades MODIFY COLUMN id int auto_increment NOT NULL;


-- u217299043_desarrollo.actividades

CREATE TABLE `actividades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `fecha` varchar(250) NOT NULL,
  `cantidad_mesas` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- u217299043_desarrollo.comentario definition

CREATE TABLE `comentario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_curso` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `fecha` varchar(250) NOT NULL,
  `comentario` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- u217299043_desarrollo.consolidacion definition

CREATE TABLE `consolidacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_padre_espiritual` int(11) NOT NULL,
  `id_persona_consolidar` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.curso definition

CREATE TABLE `curso` (
  `id` int(11) NOT NULL,
  `fechafin` varchar(255) DEFAULT NULL,
  `fechainicio` varchar(255) DEFAULT NULL,
  `idpago` int(11) DEFAULT NULL,
  `nombrecurso` varchar(255) DEFAULT NULL,
  `valortotal` int(11) DEFAULT NULL,
  `comentario` tinyint(1) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.inscripcion_actividades definition

CREATE TABLE `inscripcion_actividades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  `id_actividad` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.inscripciones definition

CREATE TABLE `inscripciones` (
  `id` int(11) NOT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `id_persona` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- u217299043_desarrollo.mesa definition

CREATE TABLE `mesa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `nombre_mesa` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.notas definition

CREATE TABLE `notas` (
  `id` int(11) NOT NULL,
  `nomactividad` varchar(255) DEFAULT NULL,
  `idcurso` datetime DEFAULT NULL,
  `idpersonafk` int(11) DEFAULT NULL,
  `nota` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.pago definition

CREATE TABLE `pago` (
  `id` int(11) NOT NULL,
  `fecha_pago` varchar(26) DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `idpersona` int(11) DEFAULT NULL,
  `valor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `parametrica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_parametros` int(11) NOT NULL,
  `descripcion_parametro` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
);

-- u217299043_desarrollo.persona definition

CREATE TABLE `persona` (

  `id` int auto_increment NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `documento` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fechanacimiento` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `tipodocumento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.persona_mesa definition

CREATE TABLE `persona_mesa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_mesa` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.persona_rol definition

CREATE TABLE `persona_rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `personaid` (`id_persona`),
  KEY `personarol` (`id_rol`),
  CONSTRAINT `personaid` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`),
  CONSTRAINT `personarol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- u217299043_desarrollo.rol definition

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `descripcion_rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.sec_curso definition

CREATE TABLE `sec_curso` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- u217299043_desarrollo.sec_inscripcion definition

CREATE TABLE `sec_inscripcion` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- u217299043_desarrollo.sec_persona definition

CREATE TABLE `sec_persona` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `sec_persona_rol` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- u217299043_desarrollo.usuario definition

CREATE TABLE `usuario` (
  `idusuario` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;






-- create table TDC

CREATE TABLE `TDC` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  `dios` varchar(1000) NOT NULL,
  `dios_check` tinyint(1) NOT NULL,
  `mandato` varchar(1000) NOT NULL,
  `mandato_check` tinyint(1) NOT NULL,
  `promesa` varchar(1000) NOT NULL,
  `promesa_check` tinyint(1) NOT NULL,
  `mensaje` varchar(1000) NOT NULL,
  `mensaje_check` tinyint(1) NOT NULL,
  `aplicacion` varchar(1000) NOT NULL,  
  `aplicacion_check` tinyint(1) NOT NULL,
  `fecha_registro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE anunciadores.ministerios (
	 `id` int(11) NOT NULL AUTO_INCREMENT,
	   nombre varchar(100) NULL,
	 PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE anunciadores.persona_ministerio (
	id_persona INT NULL,
	id_ministerio INT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_a


CREATE TABLE anunciadores.posiciones_ministerios (
	id INT auto_increment NOT NULL,
	id_ministerio INT NOT NULL,
	nombre_posicion varchar(100) NULL,
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE anunciadores.servicio (
	id INT auto_increment NOT NULL,
	id_persona INT NOT NULL,
	id_posicion INT NOT NULL,
	fecha_servicio DATE NOT NULL,
	id_ministerios INT NOT NULL,
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE estudios_persona (
	id INT auto_increment NOT NULL,
	id_persona INT NULL,
	titulo varchar(100) NOT NULL,
	fecha_realizacion DATE NOT NULL,
	CONSTRAINT estudios_persona_PK PRIMARY KEY (id),
	CONSTRAINT estudios_persona_FK FOREIGN KEY (id_persona) REFERENCES anunciadb.persona(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
CREATE INDEX estudios_persona_id_IDX USING BTREE ON anunciadb.estudios_persona (id);
































