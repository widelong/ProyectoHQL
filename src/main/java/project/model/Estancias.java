package project.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estancias")
@SuppressWarnings("serial")
public class Estancias implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codEstancia;
	@ManyToOne
	@JoinColumn(name="codEstudiante")            
	Estudiantes codEstudiante;
	@ManyToOne
	@JoinColumn(name="codResidencia")            
	Residencias codResidencia;
	@Column(columnDefinition = ("date"))
	private Date fechaInicio;
	@Column(columnDefinition = ("date"))
	private Date fechaFin;
	@Column(columnDefinition = ("smallint(6)"))
	private int precioPagado;
	public int getCodEstancia() {
		return codEstancia;
	}
	public void setCodEstancia(int codEstancia) {
		this.codEstancia = codEstancia;
	}
	public Estudiantes getCodEstudiante() {
		return codEstudiante;
	}
	public void setCodEstudiante(Estudiantes codEstudiante) {
		this.codEstudiante = codEstudiante;
	}
	public Residencias getCodResidencia() {
		return codResidencia;
	}
	public void setCodResidencia(Residencias codResidencia) {
		this.codResidencia = codResidencia;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getPrecioPagado() {
		return precioPagado;
	}
	public void setPrecioPagado(int precioPagado) {
		this.precioPagado = precioPagado;
	}
	
}
