package com.anunciadores.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author valbuena
 */

@Entity
@Table(name = "consolidacion")

public class Consolidacion implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_persona")
    private int idPersona;

	@Column(name = "acepta_consolidacion")
    private Boolean aceptaConsolidacion;

    @Column(name = "horario_consolidacion_sugerido")
    private String horarioConsolidacionSugerido;

	@Column(name = "horario_consolidacion_persona")
    private String horarioConsolidacionPersona;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getHorarioConsolidacionSugerido() {
		return horarioConsolidacionSugerido;
	}

	public void setHorarioConsolidacionSugerido(String horarioConsolidacionSugerido) {
		this.horarioConsolidacionSugerido = horarioConsolidacionSugerido;
	}

	public String getHorarioConsolidacionPersona() {
		return horarioConsolidacionPersona;
	}

	public void setHorarioConsolidacionPersona(String horarioConsolidacionPersona) {
		this.horarioConsolidacionPersona = horarioConsolidacionPersona;
	}

	public Boolean getAceptaConsolidacion() {
		return aceptaConsolidacion;
	}

	public void setAceptaConsolidacion(Boolean aceptaConsolidacion) {
		this.aceptaConsolidacion = aceptaConsolidacion;
	}
}
