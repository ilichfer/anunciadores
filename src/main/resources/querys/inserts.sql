--- insert tabla rol
INSERT INTO anunciadb.rol
(id, descripcion_rol)
VALUES(1, 'ADMINISTRADOR');
INSERT INTO anunciadb.rol
(id, descripcion_rol)
VALUES(2, 'SERVIDOR');


--- insert tabla parametro Menu
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

--- insert tabla submenu
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