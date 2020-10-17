/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Roberth :)
 */
@Entity
@Table(name = "ut_clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Clientes {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(length = 100, name = "direccion")
    private String direccion;
    @Column(length = 100, name = "email")
    private String email;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_Registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
    @Column(length = 100, name = "nombre")
    private String nombre;
    @Column(length = 8, name = "telefono")
    private String telefono;

    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }

}
