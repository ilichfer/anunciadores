package com.anunciadores.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "his_consolidacion")

public class HistoricoConsolidacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "id_consolidacion")
    private int idConsolidacion;

    @Column(name = "fecha_registro_consolidacion")
    private Date fechaRegistroConsolidacion;

	@Column(name = "descripcion_consolidacion")
	private String descripcionConsolidacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConsolidacion() {
        return idConsolidacion;
    }

    public void setIdConsolidacion(int idConsolidacion) {
        this.idConsolidacion = idConsolidacion;
    }

    public Date getFechaRegistroConsolidacion() {
        return fechaRegistroConsolidacion;
    }

    public void setFechaRegistroConsolidacion(Date fechaRegistroConsolidacion) {
        this.fechaRegistroConsolidacion = fechaRegistroConsolidacion;
    }

    public String getDescripcionConsolidacion() {
        return descripcionConsolidacion;
    }

    public void setDescripcionConsolidacion(String descripcionConsolidacion) {
        this.descripcionConsolidacion = descripcionConsolidacion;
    }
}
