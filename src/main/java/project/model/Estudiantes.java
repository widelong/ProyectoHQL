package project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes")
@SuppressWarnings("serial")
public class Estudiantes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codEstudiante;
	@Column(columnDefinition = "char(9)")
	private String dni;
	@Column(length = 50)
	private String nomEstudiante;
	@Column(columnDefinition = "char(9)")
	private String telefonoEstudiante;
	public int getCodEstudiante() {
		return codEstudiante;
	}
	public void setCodEstudiante(int codEstudiante) {
		this.codEstudiante = codEstudiante;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNomEstudiante() {
		return nomEstudiante;
	}
	public void setNomEstudiante(String nomEstudiante) {
		this.nomEstudiante = nomEstudiante;
	}
	public String getTelefonoEstudiante() {
		return telefonoEstudiante;
	}
	public void setTelefonoEstudiante(String telefonoEstudiante) {
		this.telefonoEstudiante = telefonoEstudiante;
	}
	
}
