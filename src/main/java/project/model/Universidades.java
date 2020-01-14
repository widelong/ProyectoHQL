package project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "universidades")
@SuppressWarnings("serial")
public class Universidades implements Serializable {
	@Id
	@Column(columnDefinition = ("char(6)"))
	private String codUniversidad;
	@Column(length = 30)
	private String nomUniversidad;
	public String getCodUniversidad() {
		return codUniversidad;
	}
	public void setCodUniversidad(String codUniversidad) {
		this.codUniversidad = codUniversidad;
	}
	public String getNomUniversidad() {
		return nomUniversidad;
	}
	public void setNomUniversidad(String nomUniversidad) {
		this.nomUniversidad = nomUniversidad;
	}
	
}
