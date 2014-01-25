-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version 5.5.9


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
-- Definition of table `tb_area`
--

DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `cod_area` int(11) NOT NULL AUTO_INCREMENT,
  `desc_area` varchar(45) NOT NULL,
  PRIMARY KEY (`cod_area`),
  UNIQUE KEY `desc_area_UNIQUE` (`desc_area`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='Habilitado para acceso al sistema';

 
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
  KEY `fk_trabajador` (`cod_trabajador`),
  CONSTRAINT `tb_solicitud_requerimiento_ibfk_1` FOREIGN KEY (`cod_trabajador`) REFERENCES `tb_trabajador` (`cod_trabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

 
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

 
 

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

 

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 


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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='proveedor(recepcion de los articulos)';
 


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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 


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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='area(salida articulos, recepcion de articulo devuelto)';

 

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

 


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
