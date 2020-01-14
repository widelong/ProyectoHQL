package project.model;

import java.sql.Date;



import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test1 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction sad = sesion.beginTransaction();
		Residencias residencia = new Residencias();
		Estudiantes estudiante = new Estudiantes();
		Estancias estancia = new Estancias();
		ResidenciasObservaciones resiob = new ResidenciasObservaciones();
		Universidades universidad = new Universidades();
		
		estudiante.setDni("111111111");
		estudiante.setNomEstudiante("Juan 1");
		estudiante.setTelefonoEstudiante("111111111");
		sesion.save(estudiante);
		
		estancia.setCodResidencia(residencia);
		estancia.setFechaFin(new Date(2013, 4, 4));
		estancia.setFechaInicio(new Date(2012, 4, 4));
		estancia.setPrecioPagado(1000);
		sesion.save(estancia);
		
		resiob.setCodFResidencia(residencia);
		resiob.setObservaciones("La piscina esta guapa pero hay que cerrar"
				+ " el balcon porque se mato un guiri");
		sesion.save(resiob);
		
		universidad.setCodUniversidad("111111");
		universidad.setNomUniversidad("Universidad 1");
		sesion.save(universidad);
		
		residencia.setCodUniversidad(universidad);
		residencia.setComedor(1);
		residencia.setNomResidencia("Residencia 1");
		residencia.setPrecioMensual(1000);
		sesion.save(residencia);
		sad.commit();
		sesion.close();
		}
}

	

