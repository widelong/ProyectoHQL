package project.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "residenciasobservaciones")
@SuppressWarnings("serial")
public class ResidenciasObservaciones implements Serializable {
	@Id
	@GeneratedValue(generator = "myForeign")
	@GenericGenerator(name = "myForeign", strategy = "foreign", 
		parameters = {@org.hibernate.annotations.Parameter(
			name = "property", 
			value = "codFResidencia")})
	private int codResidencia;
	
	@Column(length = 200)
	private String observaciones;
	
	@OneToOne(cascade= CascadeType.PERSIST)
	@PrimaryKeyJoinColumn
	private Residencias codFResidencia; 
	
	public Residencias getCodFResidencia() {
		return codFResidencia;
	}
	
	public void setCodFResidencia(Residencias codFResidencia) {
		this.codFResidencia = codFResidencia;
	}
	
	public int getCodResidencia() {
		return codResidencia;
	}
	public void setCodResidencia(int codResidencia) {
		this.codResidencia = codResidencia;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
