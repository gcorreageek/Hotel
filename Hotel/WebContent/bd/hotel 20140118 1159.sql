-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.29


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bd_hotel
--

CREATE DATABASE IF NOT EXISTS bd_hotel;
USE bd_hotel;

--
-- Definition of table `tb_acceso_menu`
--

DROP TABLE IF EXISTS `tb_acceso_menu`;
CREATE TABLE `tb_acceso_menu` (
  `cod_accesomenu` int(11) NOT NULL AUTO_INCREMENT,
  `cod_menu` int(11) NOT NULL,
  `cod_cargo` int(11) NOT NULL,
  `habilitado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_accesomenu`),
  UNIQUE KEY `fk_accesomenu_codcargo_codmenu` (`cod_menu`,`cod_cargo`),
  KEY `fk_codcargo_cargo_idx` (`cod_cargo`),
  KEY `fk_codmenu_menu_idx` (`cod_menu`),
  CONSTRAINT `fk_codcargo_cargo` FOREIGN KEY (`cod_cargo`) REFERENCES `tb_cargo` (`cod_cargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_codmenu_menu` FOREIGN KEY (`cod_menu`) REFERENCES `tb_menu` (`cod_menu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_acceso_menu`
--

/*!40000 ALTER TABLE `tb_acceso_menu` DISABLE KEYS */;
INSERT INTO `tb_acceso_menu` (`cod_accesomenu`,`cod_menu`,`cod_cargo`,`habilitado`) VALUES 
 (1,1,1,'Habilitado'),
 (2,2,1,'Habilitado'),
 (3,3,1,'Habilitado'),
 (4,4,1,'Habilitado'),
 (5,5,1,'Habilitado'),
 (6,6,1,'Habilitado'),
 (7,7,1,'Habilitado'),
 (8,8,1,'Habilitado'),
 (9,9,1,'Habilitado'),
 (10,10,1,'Habilitado'),
 (11,11,1,'Habilitado'),
 (12,12,1,'Habilitado'),
 (13,13,1,'Habilitado'),
 (14,14,1,'Habilitado'),
 (15,15,1,'Habilitado'),
 (19,16,1,'Habilitado'),
 (20,17,1,'Habilitado'),
 (21,18,1,'Habilitado'),
 (22,19,1,'Habilitado'),
 (23,20,1,'Habilitado'),
 (31,5,28,'Desabilitado'),
 (32,1,28,'Habilitado'),
 (33,2,28,'Habilitado'),
 (34,4,28,'Habilitado'),
 (35,7,28,'Habilitado'),
 (37,2,29,'Habilitado'),
 (38,5,27,'Habilitado'),
 (40,1,27,'Habilitado'),
 (41,3,27,'Habilitado'),
 (43,6,27,'Habilitado'),
 (44,10,27,'Habilitado'),
 (45,19,27,'Habilitado'),
 (46,14,27,'Habilitado'),
 (47,15,27,'Habilitado'),
 (48,11,27,'Habilitado'),
 (49,13,27,'Habilitado'),
 (50,12,27,'Habilitado'),
 (51,21,1,'Desabilitado'),
 (52,22,1,'Desabilitado'),
 (53,23,1,'Desabilitado');
/*!40000 ALTER TABLE `tb_acceso_menu` ENABLE KEYS */;


--
-- Definition of table `tb_area`
--

DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `cod_area` int(11) NOT NULL AUTO_INCREMENT,
  `desc_area` varchar(45) NOT NULL,
  PRIMARY KEY (`cod_area`),
  UNIQUE KEY `desc_area_UNIQUE` (`desc_area`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_area`
--

/*!40000 ALTER TABLE `tb_area` DISABLE KEYS */;
INSERT INTO `tb_area` (`cod_area`,`desc_area`) VALUES 
 (1,'Administracion del Sistema'),
 (4,'Contabilidad'),
 (5,'Externa'),
 (3,'Logistica'),
 (2,'Sistemas');
/*!40000 ALTER TABLE `tb_area` ENABLE KEYS */;


--
-- Definition of table `tb_articulo`
--

DROP TABLE IF EXISTS `tb_articulo`;
CREATE TABLE `tb_articulo` (
  `cod_articulo` int(11) NOT NULL AUTO_INCREMENT,
  `desc_articulo` varchar(45) DEFAULT NULL,
  `unidadMedida` varchar(45) DEFAULT NULL,
  `tipo_articulo` varchar(45) DEFAULT NULL,
  `stock_articulo` int(11) DEFAULT '0',
  `habilitado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_articulo`),
  UNIQUE KEY `desc_articulo_umedidad_UNIQUE` (`desc_articulo`,`unidadMedida`),
  KEY `fk_unidadMedida` (`unidadMedida`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_articulo`
--

/*!40000 ALTER TABLE `tb_articulo` DISABLE KEYS */;
INSERT INTO `tb_articulo` (`cod_articulo`,`desc_articulo`,`unidadMedida`,`tipo_articulo`,`stock_articulo`,`habilitado`) VALUES 
 (1,'Trapeador','Unidad',NULL,1120,'Habilitado'),
 (2,'Escoba','Unidad',NULL,0,'Habilitado'),
 (3,'Detergente','Unidad',NULL,0,'Habilitado'),
 (5,'Cera para piso','Unidad',NULL,0,'Habilitado'),
 (6,'Ganchos','Docena',NULL,0,'Habilitado'),
 (7,'Quita sarro','Unidad',NULL,0,'Habilitado'),
 (8,'Quita manchas','Unidad',NULL,0,'Habilitado'),
 (9,'Toalla','Unidad',NULL,0,'Habilitado'),
 (10,'Cepillo','Unidad',NULL,0,'Habilitado'),
 (11,'Jabon','Unidad',NULL,0,'Habilitado');
/*!40000 ALTER TABLE `tb_articulo` ENABLE KEYS */;


--
-- Definition of table `tb_articulo_proveedor`
--

DROP TABLE IF EXISTS `tb_articulo_proveedor`;
CREATE TABLE `tb_articulo_proveedor` (
  `cod_articulo_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `cod_articulo` int(11) DEFAULT NULL,
  `cod_proveedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_articulo_proveedor`),
  UNIQUE KEY `fk_codproveedor_codproducot_unicos` (`cod_articulo`,`cod_proveedor`),
  KEY `fk_codarticulo_articulo_idx` (`cod_articulo`),
  KEY `fk_codproveedor_proveedor_idx` (`cod_proveedor`),
  CONSTRAINT `fk_codarticulo_articulo` FOREIGN KEY (`cod_articulo`) REFERENCES `tb_articulo` (`cod_articulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_codproveedor_proveedor` FOREIGN KEY (`cod_proveedor`) REFERENCES `tb_proveedor` (`cod_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_articulo_proveedor`
--

/*!40000 ALTER TABLE `tb_articulo_proveedor` DISABLE KEYS */;
INSERT INTO `tb_articulo_proveedor` (`cod_articulo_proveedor`,`cod_articulo`,`cod_proveedor`) VALUES 
 (1,1,1);
/*!40000 ALTER TABLE `tb_articulo_proveedor` ENABLE KEYS */;


--
-- Definition of table `tb_cargo`
--

DROP TABLE IF EXISTS `tb_cargo`;
CREATE TABLE `tb_cargo` (
  `cod_cargo` int(11) NOT NULL AUTO_INCREMENT,
  `desc_cargo` varchar(45) DEFAULT NULL,
  `cod_area` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_cargo`),
  UNIQUE KEY `uniq_area_cargo_unico` (`desc_cargo`,`cod_area`),
  KEY `fk_codarea_cargo_idx` (`cod_area`),
  CONSTRAINT `fk_codarea_cargo` FOREIGN KEY (`cod_area`) REFERENCES `tb_area` (`cod_area`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cargo`
--

/*!40000 ALTER TABLE `tb_cargo` DISABLE KEYS */;
INSERT INTO `tb_cargo` (`cod_cargo`,`desc_cargo`,`cod_area`) VALUES 
 (1,'Administrador',1),
 (29,'Asistente de Logistica',3),
 (27,'DBA',2),
 (30,'Externo',5),
 (28,'Jefe de Logistica',3),
 (4,'pruea 2',NULL),
 (5,'pruea 3',NULL),
 (19,'pruea 3',1),
 (6,'pruea 4',NULL),
 (7,'pruea 6',NULL),
 (22,'pruea 6',1);
/*!40000 ALTER TABLE `tb_cargo` ENABLE KEYS */;


--
-- Definition of table `tb_cotizacion`
--

DROP TABLE IF EXISTS `tb_cotizacion`;
CREATE TABLE `tb_cotizacion` (
  `cod_cotizacion` int(11) NOT NULL AUTO_INCREMENT,
  `obs_cotizacion` text,
  `cod_trabajador` int(11) DEFAULT NULL,
  `fecharegistro_cotizacion` datetime DEFAULT NULL,
  `cod_proveedor` int(11) DEFAULT NULL,
  `tipo_cotizacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_cotizacion`),
  KEY `fk_cotizacion_cod_trabajador_idx` (`cod_trabajador`),
  KEY `fk_cotizacion_cod_proveedor_idx` (`cod_proveedor`),
  CONSTRAINT `fk_cotizacion_cod_proveedor` FOREIGN KEY (`cod_proveedor`) REFERENCES `tb_proveedor` (`cod_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cotizacion_cod_trabajador` FOREIGN KEY (`cod_trabajador`) REFERENCES `tb_trabajador` (`cod_trabajador`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cotizacion`
--

/*!40000 ALTER TABLE `tb_cotizacion` DISABLE KEYS */;
INSERT INTO `tb_cotizacion` (`cod_cotizacion`,`obs_cotizacion`,`cod_trabajador`,`fecharegistro_cotizacion`,`cod_proveedor`,`tipo_cotizacion`) VALUES 
 (1,NULL,1,'2014-01-17 04:56:24',1,NULL),
 (2,NULL,1,'2014-01-17 06:08:16',1,NULL);
/*!40000 ALTER TABLE `tb_cotizacion` ENABLE KEYS */;


--
-- Definition of table `tb_cotizacion_detalle`
--

DROP TABLE IF EXISTS `tb_cotizacion_detalle`;
CREATE TABLE `tb_cotizacion_detalle` (
  `cod_detallecotizacion` int(11) NOT NULL AUTO_INCREMENT,
  `cant_detallecotizacion` int(11) DEFAULT NULL,
  `cod_cotizacion` int(11) NOT NULL,
  `cod_articulo_proveedor` int(11) NOT NULL,
  PRIMARY KEY (`cod_detallecotizacion`),
  KEY `fk_Detalle_Cotizacion_Cotizacion1` (`cod_cotizacion`),
  KEY `fk_detalle_cotizacion_cod_articuloproveedor_idx` (`cod_articulo_proveedor`),
  CONSTRAINT `fk_detalle_cotizacion_cod_articuloproveedor` FOREIGN KEY (`cod_articulo_proveedor`) REFERENCES `tb_articulo_proveedor` (`cod_articulo_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Detalle_Cotizacion_Cotizacion1` FOREIGN KEY (`cod_cotizacion`) REFERENCES `tb_cotizacion` (`cod_cotizacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cotizacion_detalle`
--

/*!40000 ALTER TABLE `tb_cotizacion_detalle` DISABLE KEYS */;
INSERT INTO `tb_cotizacion_detalle` (`cod_detallecotizacion`,`cant_detallecotizacion`,`cod_cotizacion`,`cod_articulo_proveedor`) VALUES 
 (1,100,1,1),
 (2,100,2,1);
/*!40000 ALTER TABLE `tb_cotizacion_detalle` ENABLE KEYS */;


--
-- Definition of table `tb_informe_externo`
--

DROP TABLE IF EXISTS `tb_informe_externo`;
CREATE TABLE `tb_informe_externo` (
  `cod_informe_externo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_informe_externo` datetime DEFAULT NULL,
  `tipo_informe_externo` varchar(30) DEFAULT NULL,
  `cod_trabajador` int(11) DEFAULT NULL,
  `cod_ordencompra` int(11) DEFAULT NULL,
  `obs_informeexterno` text,
  PRIMARY KEY (`cod_informe_externo`),
  KEY `fk_trabajador_inf_ext_idx` (`cod_trabajador`),
  KEY `fk_ordencompra_cod_oc_idx` (`cod_ordencompra`),
  CONSTRAINT `fk_ordencompra_cod_oc` FOREIGN KEY (`cod_ordencompra`) REFERENCES `tb_ordencompra` (`cod_OrdenCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_inf_ext` FOREIGN KEY (`cod_trabajador`) REFERENCES `tb_trabajador` (`cod_trabajador`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='proveedor(recepcion de los articulos)';

--
-- Dumping data for table `tb_informe_externo`
--

/*!40000 ALTER TABLE `tb_informe_externo` DISABLE KEYS */;
INSERT INTO `tb_informe_externo` (`cod_informe_externo`,`fecha_informe_externo`,`tipo_informe_externo`,`cod_trabajador`,`cod_ordencompra`,`obs_informeexterno`) VALUES 
 (2,'2014-01-17 05:36:25','Entrada',1,1,'prueba3'),
 (3,'2014-01-17 06:11:19','Salida',1,1,'');
/*!40000 ALTER TABLE `tb_informe_externo` ENABLE KEYS */;


--
-- Definition of table `tb_informe_externo_detalle`
--

DROP TABLE IF EXISTS `tb_informe_externo_detalle`;
CREATE TABLE `tb_informe_externo_detalle` (
  `cod_detalle_informe_externo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_informe_externo` int(11) DEFAULT NULL,
  `cod_detalle_ordencompra` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_detalle_informe_externo`),
  KEY `fk_informeext_id_idx` (`cod_informe_externo`),
  KEY `fk_codoc_ext_id_idx` (`cod_detalle_ordencompra`),
  CONSTRAINT `fk_codoc_ext_id` FOREIGN KEY (`cod_detalle_ordencompra`) REFERENCES `tb_ordencompra_detalle` (`cod_DetalleOrdenCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_informeext_id` FOREIGN KEY (`cod_informe_externo`) REFERENCES `tb_informe_externo` (`cod_informe_externo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_informe_externo_detalle`
--

/*!40000 ALTER TABLE `tb_informe_externo_detalle` DISABLE KEYS */;
INSERT INTO `tb_informe_externo_detalle` (`cod_detalle_informe_externo`,`cod_informe_externo`,`cod_detalle_ordencompra`) VALUES 
 (1,2,1),
 (2,3,1);
/*!40000 ALTER TABLE `tb_informe_externo_detalle` ENABLE KEYS */;


--
-- Definition of table `tb_informe_interno`
--

DROP TABLE IF EXISTS `tb_informe_interno`;
CREATE TABLE `tb_informe_interno` (
  `cod_informe_interno` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_informe_interno` datetime DEFAULT NULL,
  `tipo_informe_interno` varchar(30) DEFAULT NULL,
  `cod_trabajador` int(11) DEFAULT NULL,
  `cod_solicitud_requerimiento` int(11) DEFAULT NULL,
  `obs_informeinterno` text,
  PRIMARY KEY (`cod_informe_interno`),
  KEY `fk_trabajador_inf_int_idx` (`cod_trabajador`),
  KEY `fk_solicitud_requerimiento_inf_int_idx` (`cod_solicitud_requerimiento`),
  CONSTRAINT `fk_solicitud_requerimiento_inf_int` FOREIGN KEY (`cod_solicitud_requerimiento`) REFERENCES `tb_solicitud_requerimiento` (`cod_solicitudRequerimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_inf_int` FOREIGN KEY (`cod_trabajador`) REFERENCES `tb_trabajador` (`cod_trabajador`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='area(salida articulos, recepcion de articulo devuelto)';

--
-- Dumping data for table `tb_informe_interno`
--

/*!40000 ALTER TABLE `tb_informe_interno` DISABLE KEYS */;
INSERT INTO `tb_informe_interno` (`cod_informe_interno`,`fecha_informe_interno`,`tipo_informe_interno`,`cod_trabajador`,`cod_solicitud_requerimiento`,`obs_informeinterno`) VALUES 
 (2,'2014-01-17 06:06:52','Salida',1,1,'prueba4'),
 (3,'2014-01-17 06:13:52','Salida',1,3,''),
 (4,'2014-01-17 06:20:23','Entrada',1,3,'prueba4');
/*!40000 ALTER TABLE `tb_informe_interno` ENABLE KEYS */;


--
-- Definition of table `tb_informe_interno_detalle`
--

DROP TABLE IF EXISTS `tb_informe_interno_detalle`;
CREATE TABLE `tb_informe_interno_detalle` (
  `cod_detalle_informe_interno` int(11) NOT NULL AUTO_INCREMENT,
  `cod_informe_interno` int(11) DEFAULT NULL,
  `cod_detalle_solicitud_requerimiento` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_detalle_informe_interno`),
  KEY `fk_informeint_pk_idx` (`cod_informe_interno`),
  KEY `fk_detallesolicitud_requerimiento_pk_idx` (`cod_detalle_solicitud_requerimiento`),
  CONSTRAINT `fk_detallesolicitud_requerimiento_pk` FOREIGN KEY (`cod_detalle_solicitud_requerimiento`) REFERENCES `tb_solicitud_requerimiento_detalle` (`cod_detalleRequerimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_informeint_pk` FOREIGN KEY (`cod_informe_interno`) REFERENCES `tb_informe_interno` (`cod_informe_interno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_informe_interno_detalle`
--

/*!40000 ALTER TABLE `tb_informe_interno_detalle` DISABLE KEYS */;
INSERT INTO `tb_informe_interno_detalle` (`cod_detalle_informe_interno`,`cod_informe_interno`,`cod_detalle_solicitud_requerimiento`) VALUES 
 (1,2,1),
 (2,3,3),
 (3,4,3);
/*!40000 ALTER TABLE `tb_informe_interno_detalle` ENABLE KEYS */;


--
-- Definition of table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `cod_menu` int(11) NOT NULL AUTO_INCREMENT,
  `nom_menu` varchar(200) DEFAULT NULL,
  `url_menu` varchar(200) DEFAULT NULL,
  `icono_menu` varchar(200) DEFAULT NULL,
  `tipo_menu` int(11) DEFAULT NULL,
  `id_submenu` int(11) DEFAULT NULL,
  `orden_menu` int(11) DEFAULT NULL,
  `master_menu` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_menu`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_menu`
--

/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` (`cod_menu`,`nom_menu`,`url_menu`,`icono_menu`,`tipo_menu`,`id_submenu`,`orden_menu`,`master_menu`) VALUES 
 (1,'Mantenimientos','#','icon-edit',1,1,2,1),
 (2,'Articulo','mainArticulo',NULL,2,1,3,1),
 (3,'Proveedor','mainProveedor',NULL,2,1,4,1),
 (4,'Solicitud Req','#','icon-book',1,4,10,1),
 (5,'Inicio','inicio','icon-dashboard',1,5,1,0),
 (6,'Articulo Proveedor','mainArticuloProveedor',NULL,2,1,5,1),
 (7,'Nuevo Solicitud Req','nuevoSolicitudReq',NULL,2,4,11,1),
 (8,'Evaluar Solicitud Req','evaluarSolicitudReq',NULL,2,4,12,1),
 (9,'Cotizacion','mainCotizacion','icon-table',1,9,13,0),
 (10,'Orden de Compra','mainOrdenCompra','icon-th',1,10,14,0),
 (11,'Informe Interno','#','icon-tint',1,11,15,1),
 (12,'Entrada','mainInformeInternoEntrada',NULL,2,11,17,1),
 (13,'Salida','mainInformeInternoSalida',NULL,2,11,16,1),
 (14,'Informe Externa','#','icon-star',1,14,18,1),
 (15,'Entrada','mainInformeExternoEntrada',NULL,2,14,19,1),
 (16,'Salida','mainInformeExternoSalida',NULL,2,14,20,1),
 (17,'Area','mainArea',NULL,2,1,6,1),
 (18,'Cargo','mainCargo',NULL,2,1,7,1),
 (19,'Trabajador','mainTrabajador',NULL,2,1,8,1),
 (20,'Acceso','mainAcceso',NULL,2,1,9,1),
 (21,'Reportes','#','icon-font',1,21,21,1),
 (22,'Articulo','productos',NULL,2,21,22,1),
 (23,'Informe','informe',NULL,2,21,23,1);
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;


--
-- Definition of table `tb_ordencompra`
--

DROP TABLE IF EXISTS `tb_ordencompra`;
CREATE TABLE `tb_ordencompra` (
  `cod_OrdenCompra` int(11) NOT NULL AUTO_INCREMENT,
  `fecharegistro_ordencompra` datetime DEFAULT NULL,
  `cod_trabajador` int(11) DEFAULT NULL,
  `cod_cotizacion` int(11) NOT NULL,
  `cod_proveedor` int(11) DEFAULT NULL,
  `estado_ordencompra` varchar(100) NOT NULL,
  `tipo_ordencompra` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_OrdenCompra`),
  KEY `fk_Orden_Compra_Cotizacion1` (`cod_cotizacion`),
  KEY `fk_orden_compa_idusario_idx` (`cod_trabajador`),
  KEY `fk_ordem_compra_codproveedor_idx` (`cod_proveedor`),
  CONSTRAINT `fk_ordem_compra_codproveedor` FOREIGN KEY (`cod_proveedor`) REFERENCES `tb_proveedor` (`cod_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orden_compa_idusario` FOREIGN KEY (`cod_trabajador`) REFERENCES `tb_trabajador` (`cod_trabajador`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Orden_Compra_Cotizacion1` FOREIGN KEY (`cod_cotizacion`) REFERENCES `tb_cotizacion` (`cod_cotizacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_ordencompra`
--

/*!40000 ALTER TABLE `tb_ordencompra` DISABLE KEYS */;
INSERT INTO `tb_ordencompra` (`cod_OrdenCompra`,`fecharegistro_ordencompra`,`cod_trabajador`,`cod_cotizacion`,`cod_proveedor`,`estado_ordencompra`,`tipo_ordencompra`) VALUES 
 (1,'2014-01-17 05:26:52',1,1,1,'Pendiente',NULL),
 (2,'2014-01-17 06:08:43',1,2,1,'Pendiente',NULL),
 (3,'2014-01-17 06:08:56',1,2,1,'Pendiente',NULL);
/*!40000 ALTER TABLE `tb_ordencompra` ENABLE KEYS */;


--
-- Definition of table `tb_ordencompra_detalle`
--

DROP TABLE IF EXISTS `tb_ordencompra_detalle`;
CREATE TABLE `tb_ordencompra_detalle` (
  `cod_DetalleOrdenCompra` int(11) NOT NULL AUTO_INCREMENT,
  `cod_ordenCompra` int(11) DEFAULT NULL,
  `cod_articulo_proveedor` int(11) DEFAULT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_DetalleOrdenCompra`),
  KEY `fk_ordenCompra` (`cod_ordenCompra`),
  KEY `fk_articulo` (`cod_articulo_proveedor`),
  CONSTRAINT `fk_ordenCompra` FOREIGN KEY (`cod_ordenCompra`) REFERENCES `tb_ordencompra` (`cod_OrdenCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordencompra_detalle_cod_articuloproveedor` FOREIGN KEY (`cod_articulo_proveedor`) REFERENCES `tb_articulo_proveedor` (`cod_articulo_proveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_ordencompra_detalle`
--

/*!40000 ALTER TABLE `tb_ordencompra_detalle` DISABLE KEYS */;
INSERT INTO `tb_ordencompra_detalle` (`cod_DetalleOrdenCompra`,`cod_ordenCompra`,`cod_articulo_proveedor`,`cantidad`) VALUES 
 (1,1,1,'100'),
 (2,2,1,'100'),
 (3,3,1,'100');
/*!40000 ALTER TABLE `tb_ordencompra_detalle` ENABLE KEYS */;


--
-- Definition of table `tb_proveedor`
--

DROP TABLE IF EXISTS `tb_proveedor`;
CREATE TABLE `tb_proveedor` (
  `cod_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `raz_social` varchar(150) NOT NULL,
  `ruc` varchar(15) DEFAULT NULL,
  `telefono` varchar(14) DEFAULT NULL,
  `correo` varchar(45) NOT NULL,
  `habilitado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_proveedor`),
  UNIQUE KEY `raz_social_UNIQUE` (`raz_social`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_proveedor`
--

/*!40000 ALTER TABLE `tb_proveedor` DISABLE KEYS */;
INSERT INTO `tb_proveedor` (`cod_proveedor`,`raz_social`,`ruc`,`telefono`,`correo`,`habilitado`) VALUES 
 (1,'Corporacion Wong','10457205550','540-2606','edinson@gmail.com','Habilitado');
/*!40000 ALTER TABLE `tb_proveedor` ENABLE KEYS */;


--
-- Definition of table `tb_solicitud_requerimiento`
--

DROP TABLE IF EXISTS `tb_solicitud_requerimiento`;
CREATE TABLE `tb_solicitud_requerimiento` (
  `cod_solicitudRequerimiento` int(11) NOT NULL AUTO_INCREMENT,
  `cod_trabajador` int(11) DEFAULT NULL,
  `comentario_solicitud_requerimiento` text,
  `tipo_solicitud_requerimiento` varchar(45) DEFAULT NULL,
  `fechaDevolucion_solicitud_requerimiento` date DEFAULT NULL,
  `fechaRegistro_solicitud_requerimiento` datetime DEFAULT NULL,
  `fechaEntrega_solicitud_requerimiento` date DEFAULT NULL,
  `estado_solicitud_requerimiento` varchar(45) DEFAULT NULL,
  `fechaEvaluacion_solicitud_requerimiento` datetime DEFAULT NULL,
  `comentarioevaluacion_solicitud_requerimiento` text,
  PRIMARY KEY (`cod_solicitudRequerimiento`),
  KEY `fk_trabajador` (`cod_trabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_solicitud_requerimiento`
--

/*!40000 ALTER TABLE `tb_solicitud_requerimiento` DISABLE KEYS */;
INSERT INTO `tb_solicitud_requerimiento` (`cod_solicitudRequerimiento`,`cod_trabajador`,`comentario_solicitud_requerimiento`,`tipo_solicitud_requerimiento`,`fechaDevolucion_solicitud_requerimiento`,`fechaRegistro_solicitud_requerimiento`,`fechaEntrega_solicitud_requerimiento`,`estado_solicitud_requerimiento`,`fechaEvaluacion_solicitud_requerimiento`,`comentarioevaluacion_solicitud_requerimiento`) VALUES 
 (1,1,'prueba','Abastecimiento',NULL,'2014-01-17 04:52:11','2014-01-17','Atentido','2014-01-17 04:55:24','prueba2'),
 (2,1,'','Prestamo','2014-01-17','2014-01-17 06:11:49','2014-01-17','Sin Atender',NULL,NULL),
 (3,1,'s','Prestamo','2014-01-17','2014-01-17 06:13:10','2014-01-17','Devuelto','2014-01-17 06:13:31','de');
/*!40000 ALTER TABLE `tb_solicitud_requerimiento` ENABLE KEYS */;


--
-- Definition of table `tb_solicitud_requerimiento_detalle`
--

DROP TABLE IF EXISTS `tb_solicitud_requerimiento_detalle`;
CREATE TABLE `tb_solicitud_requerimiento_detalle` (
  `cod_detalleRequerimiento` int(11) NOT NULL AUTO_INCREMENT,
  `cod_solicitudRequerimiento` int(11) DEFAULT NULL,
  `cod_articulo` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_detalleRequerimiento`),
  KEY `fk_solicitud_requerimiento` (`cod_solicitudRequerimiento`),
  KEY `fk_articulo` (`cod_articulo`),
  CONSTRAINT `tb_solicitud_requerimiento_detalle_ibfk_2` FOREIGN KEY (`cod_articulo`) REFERENCES `tb_articulo` (`cod_articulo`),
  CONSTRAINT `tb_solicitud_requerimiento_fk_cod_solicitud_requerimiento` FOREIGN KEY (`cod_solicitudRequerimiento`) REFERENCES `tb_solicitud_requerimiento` (`cod_solicitudRequerimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_solicitud_requerimiento_detalle`
--

/*!40000 ALTER TABLE `tb_solicitud_requerimiento_detalle` DISABLE KEYS */;
INSERT INTO `tb_solicitud_requerimiento_detalle` (`cod_detalleRequerimiento`,`cod_solicitudRequerimiento`,`cod_articulo`,`cantidad`) VALUES 
 (1,1,1,21),
 (2,2,1,1),
 (3,3,1,2);
/*!40000 ALTER TABLE `tb_solicitud_requerimiento_detalle` ENABLE KEYS */;


--
-- Definition of table `tb_trabajador`
--

DROP TABLE IF EXISTS `tb_trabajador`;
CREATE TABLE `tb_trabajador` (
  `cod_trabajador` int(11) NOT NULL AUTO_INCREMENT,
  `nom_trabajador` varchar(45) DEFAULT NULL,
  `correo_trabajador` varchar(200) DEFAULT NULL,
  `cod_cargo` int(11) NOT NULL,
  `usu_trabajador` varchar(200) DEFAULT NULL,
  `pass_trabajador` varchar(150) DEFAULT NULL,
  `habilitado` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`cod_trabajador`),
  UNIQUE KEY `usu_trabajador_UNIQUE` (`usu_trabajador`),
  KEY `fk_cargo` (`cod_cargo`),
  CONSTRAINT `tb_trabajador_fk_cod_cargo` FOREIGN KEY (`cod_cargo`) REFERENCES `tb_cargo` (`cod_cargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_trabajador_ibfk_1` FOREIGN KEY (`cod_cargo`) REFERENCES `tb_cargo` (`cod_cargo`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1 COMMENT='Habilitado para acceso al sistema';

--
-- Dumping data for table `tb_trabajador`
--

/*!40000 ALTER TABLE `tb_trabajador` DISABLE KEYS */;
INSERT INTO `tb_trabajador` (`cod_trabajador`,`nom_trabajador`,`correo_trabajador`,`cod_cargo`,`usu_trabajador`,`pass_trabajador`,`habilitado`) VALUES 
 (1,'Adm','edinson@gmail.com',1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997','Habilitado'),
 (2,'Gustavo','edinson@gmail.com',29,'gcorreageek1',NULL,'Habilitado'),
 (4,'Gustavo','edinson@gmail.com',1,'gcorreacaja',NULL,'Habilitado'),
 (6,'Gustavo','ddddjj',28,'gcorreacaja1',NULL,'Desabilitado'),
 (8,'gustavo','edinson@gmail.com',29,'nn9','8cb2237d0679ca88db6464eac60da96345513964','Desabilitado'),
 (9,'Gustavo','ggg',27,'gcorreageek4','aa743a0aaec8f7d7a1f01442503957f4d7a2d634','Habilitado'),
 (10,'gustavo','edinson@gmail.com',29,'gcorreageek67','aa743a0aaec8f7d7a1f01442503957f4d7a2d634','Habilitado'),
 (11,'Gustavo','edinson@gmail.com',1,'gcorreageek98','8cb2237d0679ca88db6464eac60da96345513964','Habilitado'),
 (12,'Gustavo','edinson@gmail.com',22,'gcorreageek122','f7c3bc1d808e04732adf679965ccc34ca7ae3441','Habilitado'),
 (13,'Gustavo','edinson@gmail.com',29,'nn12','da39a3ee5e6b4b0d3255bfef95601890afd80709','Habilitado'),
 (14,'Gustavo','edinson@gmail.com',29,'gcorreageek1234','8cb2237d0679ca88db6464eac60da96345513964','Habilitado'),
 (15,'Gustavo','edinson@gmail.com',1,'gusigus','d033e22ae348aeb5660fc2140aec35850c4da997','Habilitado');
/*!40000 ALTER TABLE `tb_trabajador` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
