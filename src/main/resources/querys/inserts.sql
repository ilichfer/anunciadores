INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(1, 'menuAdministrar');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(2, 'menuUsuarios');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(3, 'menuCursos');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(4, 'menuActividades');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(5, 'menuAsistentes');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(6, 'menuServicio');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(7, 'menuTCD');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(8, 'menuConsolidacion');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(9,'menuCoordinador');
INSERT INTO anunciadb.param_menu
(id, nombre_boton_menu)
VALUES(10,'menuVersiculo');


INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(1, 1, 'menuAdministrar');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(2, 2, 'menuUsuarios');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(3, 3, 'subMenuListarCursos');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(4, 3, 'subMenuCrearCursos');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(5, 3, 'subMenuReporteCursos');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(6, 4, 'SubMenuListActividades');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(7, 5, 'menuAsistentes');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(8, 6, 'menuServicio');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(9, 7, 'SubMenuTCDListar');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(10, 7, 'SubMenuTCDReporte');
INSERT INTO anunciadb.param_submenu
(id, id_menu, etiqueta)
VALUES(11, 8, 'SubMenuSerConsolidacion');
INSERT INTO anunciadb.param_submenu
(id, etiqueta, id_menu)
VALUES(12, 'menuCoordinador', 9);
INSERT INTO anunciadb.param_submenu
(id, etiqueta, id_menu)
VALUES(13, 'menuVersiculo', 10);



INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(1, 'true', 2, 'menuAdministrar', 1);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(2, 'true', 2, 'menuUsuarios', 2);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(3, 'true', 2, 'menuCursos', 3);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(4, 'true', 2, 'menuActividades', 4);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(5, 'true', 2, 'menuAsistentes', 5);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(6, 'true', 2, 'menuServicio', 6);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(7, 'true', 2, 'menuTCD', 7);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(8, 'true', 2, 'menuConsolidacion', 8);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(9, 'false', 1, 'menuAdministrar', 1);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(10, 'true', 1, 'menuUsuarios', 2);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(11, 'true', 1, 'menuCursos', 3);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(12, 'true', 1, 'menuActividades', 4);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(13, 'true', 1, 'menuAsistentes', 5);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(14, 'true', 1, 'menuServicio', 6);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(15, 'true', 1, 'menuTCD', 7);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(16, 'true', 1, 'menuConsolidacion', 8);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(17, 'true', 87, 'menuAdministrar', 1);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(18, 'true', 87, 'menuUsuarios', 2);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(19, 'true', 87, 'menuCursos', 3);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(20, 'true', 87, 'menuActividades', 4);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(21, 'true', 87, 'menuAsistentes', 5);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(22, 'true', 87, 'menuServicio', 6);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(23, 'true', 87, 'menuTCD', 7);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(24, 'true', 87, 'menuConsolidacion', 8);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(25, 'true', 130, 'menuAdministrar', 1);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(26, 'true', 130, 'menuUsuarios', 2);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(27, 'true', 130, 'menuCursos', 3);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(28, 'true', 130, 'menuActividades', 4);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(29, 'true', 130, 'menuAsistentes', 5);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(30, 'true', 130, 'menuServicio', 6);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(31, 'true', 130, 'menuTCD', 7);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(32, 'true', 130, 'menuConsolidacion', 8);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(33, 'false', 133, 'menuAdministrar', 1);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(34, 'true', 133, 'menuUsuarios', 2);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(35, 'true', 133, 'menuCursos', 3);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(36, 'true', 133, 'menuActividades', 4);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(37, 'true', 133, 'menuAsistentes', 5);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(38, 'true', 133, 'menuServicio', 6);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(39, 'true', 133, 'menuTCD', 7);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(40, 'true', 133, 'menuConsolidacion', 8);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(41, 'true', 2, 'menuCoordinador', 9);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(42, 'true', 133, 'menuCoordinador', 9);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(43, 'true', 130, 'menuCoordinador', 9);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(44, 'false', 117, 'menuAdministrar', 1);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(45, 'false', 117, 'menuUsuarios', 2);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(46, 'false', 117, 'menuCursos', 3);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(47, 'false', 117, 'menuActividades', 4);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(48, 'false', 117, 'menuAsistentes', 5);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(49, 'false', 117, 'menuServicio', 6);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(50, 'true', 117, 'menuTCD', 7);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(51, 'false', 117, 'menuConsolidacion', 8);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(52, 'false', 117, 'menuCoordinador', 9);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(53, 'true', 87, 'menuCoordinador', 9);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(54, 'true', 133, 'menuVersiculo', 10);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(55, 'true', 130, 'menuVersiculo', 10);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(56, 'true', 87, 'menuVersiculo', 10);
INSERT INTO anunciadb.permisos_menu
(id, estado, id_persona, nombre_boton_menu, id_menu)
VALUES(57, 'true', 2, 'menuVersiculo', 10);


-- GÉNERO
INSERT INTO parametros (id, descripcion, grupo) VALUES
(1, 'Masculino', 'GENERO'),
(2, 'Femenino', 'GENERO'),
(3, 'Otro', 'GENERO');

-- ESTADO CIVIL
INSERT INTO parametros (id, descripcion, grupo) VALUES
(4, 'Soltero(a)', 'ESTADOCIVIL'),
(5, 'Casado(a)', 'ESTADOCIVIL'),
(6, 'Divorciado(a)', 'ESTADOCIVIL'),
(7, 'Viudo(a)', 'ESTADOCIVIL');

-- ESCOLARIDAD
INSERT INTO parametros (id, descripcion, grupo) VALUES
(8, 'Primaria', 'ESCOLARIDAD'),
(9, 'Secundaria', 'ESCOLARIDAD'),
(10, 'Preparatoria', 'ESCOLARIDAD'),
(11, 'Universidad', 'ESCOLARIDAD'),
(12, 'Posgrado', 'ESCOLARIDAD');

-- DOCUMENTO
INSERT INTO parametros (id, descripcion, grupo) VALUES
(13, 'Cédula de ciudadanía', 'DOCUMENTO'),
(14, 'Pasaporte', 'DOCUMENTO'),
(15, 'Licencia de conducción', 'DOCUMENTO'),
(16, 'Tarjeta de identidad', 'DOCUMENTO');

-- ASISTENCIA
INSERT INTO parametros (id, descripcion, grupo) VALUES
(17, 'Presente', 'ASISTENCIA'),
(18, 'Ausente', 'ASISTENCIA'),
(19, 'Justificado', 'ASISTENCIA');

