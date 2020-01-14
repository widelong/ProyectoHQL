package project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "residencias")
@SuppressWarnings("serial")
public class Residencias implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codResidencia;
	@Column(length = 30)
	private String nomResidencia;
	@ManyToOne
	@JoinColumn(name="codUniversidad")            
	Universidades codUniversidad;
	@Column(columnDefinition = "smallint(6)")
	private int precioMensual;
	@Column(columnDefinition = "tinyint(1)")
	private int Comedor;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private ResidenciasObservaciones resiObs;

	public ResidenciasObservaciones getResiObs() {
		return resiObs;
	}

	public void setResiObs(ResidenciasObservaciones resiObs) {
		this.resiObs = resiObs;
	}

	public int getCodResidencia() {
		return codResidencia;
	}

	public void setCodResidencia(int codResidencia) {
		this.codResidencia = codResidencia;
	}

	public String getNomResidencia() {
		return nomResidencia;
	}

	public void setNomResidencia(String nomResidencia) {
		this.nomResidencia = nomResidencia;
	}


	public int getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(int precioMensual) {
		this.precioMensual = precioMensual;
	}

	public int getComedor() {
		return Comedor;
	}

	public void setComedor(int comedor) {
		Comedor = comedor;
	}

	public Universidades getCodUniversidad() {
		return codUniversidad;
	}

	public void setCodUniversidad(Universidades codUniversidad) {
		this.codUniversidad = codUniversidad;
	}

}
