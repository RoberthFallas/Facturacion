-- MySQL Script generated by MySQL Workbench
-- Fri Oct 16 19:07:52 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DB_PC_Grupo3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DB_PC_Grupo3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DB_PC_Grupo3` DEFAULT CHARACTER SET utf8 ;
USE `DB_PC_Grupo3` ;

-- -----------------------------------------------------
-- Table `DB_PC_Grupo3`.`ut_productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_PC_Grupo3`.`ut_productos` (
  `id` BIGINT(20) NOT NULL,
  `descripcion` VARCHAR(100) NULL,
  `estado` BIT(1) NULL,
  `fecha_modificacion` DATETIME NULL,
  `fecha_registro` DATETIME NULL,
  `impuesto` DOUBLE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_PC_Grupo3`.`ut_productos_existencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_PC_Grupo3`.`ut_productos_existencias` (
  `id` BIGINT(20) NOT NULL,
  `cantidad` DOUBLE NULL,
  `estado` BIT(1) NULL,
  `fecha_modificacion` DATETIME NULL,
  `fecha_registro` DATETIME NULL,
  `productos_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_ut_productos_existencias_ut_productos_idx` (`productos_id` ASC) ,
  CONSTRAINT `fk_ut_productos_existencias_ut_productos`
    FOREIGN KEY (`productos_id`)
    REFERENCES `DB_PC_Grupo3`.`ut_productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_PC_Grupo3`.`ut_productos_precios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_PC_Grupo3`.`ut_productos_precios` (
  `id` BIGINT(20) NOT NULL,
  `descuento_maximo` DOUBLE NULL,
  `descuento_promocional` DOUBLE NULL,
  `estado` BIT(1) NULL,
  `fecha_modificacion` DATETIME NULL,
  `fecha_registro` DATETIME NULL,
  `precio_colones` DOUBLE NULL,
  `productos_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_ut_productos_precios_ut_productos1_idx` (`productos_id` ASC) ,
  CONSTRAINT `fk_ut_productos_precios_ut_productos1`
    FOREIGN KEY (`productos_id`)
    REFERENCES `DB_PC_Grupo3`.`ut_productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_PC_Grupo3`.`ut_clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_PC_Grupo3`.`ut_clientes` (
  `id` BIGINT(20) NOT NULL,
  `direccion` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `estado` BIT(1) NULL,
  `fecha_modificacion` DATETIME NULL,
  `fecha_registro` DATETIME NULL,
  `nombre` VARCHAR(100) NULL,
  `telefono` VARCHAR(8) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_PC_Grupo3`.`ut_facturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_PC_Grupo3`.`ut_facturas` (
  `id` BIGINT(20) NOT NULL,
  `caja` INT(11) NULL,
  `descuento_general` DOUBLE NULL,
  `estado` BIT(1) NULL,
  `fecha_modificacion` DATETIME NULL,
  `fecha_registro` DATETIME NULL,
  `clientes_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_ut_facturas_ut_clientes1_idx` (`clientes_id` ASC) ,
  CONSTRAINT `fk_ut_facturas_ut_clientes1`
    FOREIGN KEY (`clientes_id`)
    REFERENCES `DB_PC_Grupo3`.`ut_clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_PC_Grupo3`.`ut_facturas_detalles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_PC_Grupo3`.`ut_facturas_detalles` (
  `id` BIGINT(20) NOT NULL,
  `cantidad` DOUBLE NULL,
  `descuento_final` DOUBLE NULL,
  `estado` BIT(1) NULL,
  `fecha_modificacion` DATETIME NULL,
  `fecha_registro` DATETIME NULL,
  `facturas_id` BIGINT(20) NOT NULL,
  `productos_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_ut_facturas_detalles_ut_productos1_idx` (`productos_id` ASC) ,
  INDEX `fk_ut_facturas_detalles_ut_facturas1_idx` (`facturas_id` ASC) ,
  CONSTRAINT `fk_ut_facturas_detalles_ut_productos1`
    FOREIGN KEY (`productos_id`)
    REFERENCES `DB_PC_Grupo3`.`ut_productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ut_facturas_detalles_ut_facturas1`
    FOREIGN KEY (`facturas_id`)
    REFERENCES `DB_PC_Grupo3`.`ut_facturas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
