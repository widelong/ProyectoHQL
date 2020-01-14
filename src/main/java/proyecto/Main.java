package proyecto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import project.model.Estancias;
import project.model.Estudiantes;
import project.model.HibernateUtil;
import project.model.Residencias;
import project.model.ResidenciasObservaciones;
import project.model.Universidades;

public class Main {
	public static ArrayList<Universidades> universidades;
	public static ArrayList<String> codigosUni;
	public static ArrayList<String> nombresUni;
	public static ArrayList<Residencias> residencias;
	public static ArrayList<Integer> codigosResi;
	public static ArrayList<String> nombresResi;
	public static ArrayList<ResidenciasObservaciones> observaciones;
	public static ArrayList<Integer> codigosResiOb;
	public static ArrayList<String> observacionesText;
	public static ArrayList<Estudiantes> estudiantes;
	private static ArrayList<Integer> codigosEstu;
	private static ArrayList<String> nombresEstu;
	private static ArrayList<Estancias> estancias;
	private static ArrayList<Estudiantes> estuEsta;
	private static ArrayList<Residencias> resiEsta;
	private static ArrayList<Integer> codigosEsta;

	public static void main(String[] args) {
		// crear la base de datos con algunos registros
		Session checkBase = HibernateUtil.getSessionFactory().openSession();
		Query check = checkBase.createQuery("from Universidades ");
		universidades = new ArrayList<Universidades>(check.list());
		if (universidades.isEmpty()) {
			crearBase();
		}
		checkBase.close();
		Scanner scanner = new Scanner(System.in);
		int loop = 0;
		while (loop < 1) {
			loop = -1;
			Session getUnis = HibernateUtil.getSessionFactory().openSession();
			Query q = getUnis.createQuery("from Universidades ");
			universidades = new ArrayList<Universidades>(q.list());
			codigosUni = new ArrayList<String>();
			nombresUni = new ArrayList<String>();
			getUnis.close();
			for (int i = 0; i < universidades.size(); i++) {
				codigosUni.add(universidades.get(i).getCodUniversidad());
				nombresUni.add(universidades.get(i).getNomUniversidad());
			}
			Session getEstan = HibernateUtil.getSessionFactory().openSession();
			q = getEstan.createQuery("from Estancias ");
			estancias = new ArrayList<Estancias>(q.list());
			codigosEsta = new ArrayList<Integer>();
			estuEsta = new ArrayList<Estudiantes>();
			resiEsta = new ArrayList<Residencias>();
			getUnis.close();
			for (int i = 0; i < estancias.size(); i++) {
				estuEsta.add(estancias.get(i).getCodEstudiante());
				resiEsta.add(estancias.get(i).getCodResidencia());
				codigosEsta.add(estancias.get(i).getCodEstancia());
			}
			getEstan.close();
			Session getResis = HibernateUtil.getSessionFactory().openSession();
			q = getResis.createQuery("from Residencias ");
			residencias = new ArrayList<Residencias>(q.list());
			codigosResi = new ArrayList<Integer>();
			nombresResi = new ArrayList<String>();
			getResis.close();
			for (int i = 0; i < residencias.size(); i++) {
				codigosResi.add(residencias.get(i).getCodResidencia());
				nombresResi.add(residencias.get(i).getNomResidencia());
			}
			Session getObserv = HibernateUtil.getSessionFactory().openSession();
			q = getObserv.createQuery("from ResidenciasObservaciones ");
			observaciones = new ArrayList<ResidenciasObservaciones>();
			observacionesText = new ArrayList<String>();
			codigosResiOb = new ArrayList<Integer>();
			for (int i = 0; i < observaciones.size(); i++) {
				observacionesText.add(observaciones.get(i).getObservaciones());
				codigosResiOb.add(observaciones.get(i).getCodResidencia());
			}
			getObserv.close();
			Session getEstud = HibernateUtil.getSessionFactory().openSession();
			q = getEstud.createQuery("from Estudiantes ");
			estudiantes = new ArrayList<Estudiantes>(q.list());
			codigosEstu = new ArrayList<Integer>();
			nombresEstu = new ArrayList<String>();
			getResis.close();
			for (int i = 0; i < estudiantes.size(); i++) {
				codigosEstu.add(estudiantes.get(i).getCodEstudiante());
				nombresEstu.add(estudiantes.get(i).getNomEstudiante());
			}
			int i = 0;
			while (i < 1 || i > 2) {
				System.out.println("Indique con que desea trabajar: 1 = Residencias, 2 = Estancias");
				try {
					String s = scanner.nextLine();
					i = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					System.out.println("Introduce 1 o 2");
				}
			}
			String tipo = i == 1 ? "Residencias" : "Estancias";
			System.out.println("Trabajando con " + tipo);
			int j = 0;
			while (j < 1 || j > 4) {
				System.out
						.println("Indique que desea hacer: 1 = Insertar, 2 = Eliminar, 3 = Actualizar, 4 = Consultar");
				try {
					String s = scanner.nextLine();
					j = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					System.out.println("Introduce un numero valido");
				}
			}
			if (i == 1) {
				// residencias
				if (j == 1) {
					System.out.println("Insertando " + tipo);
					insertRes();
				} else if (j == 2) {
					System.out.println("Eliminando " + tipo);
					eliminRes();
				} else if (j == 3) {
					System.out.println("Actualizando " + tipo);
					actualiRes();
				} else {
					System.out.println("Consulta de " + tipo);
					consultRes();
				}
			} else {
				// estancias
				if (j == 1) {
					System.out.println("Insertando " + tipo);
					insertEst();
				} else if (j == 2) {
					System.out.println("Eliminando " + tipo);
					eliminEst();
				} else if (j == 3) {
					System.out.println("Actualizando " + tipo);
					actualiEst();
				} else {
					System.out.println("Consulta de " + tipo);
					consultEst();
				}
			}
			while (loop < 0 || loop > 1) {
				System.out.println("Indique si desea sequir trabajando: 0 = Si, 1 = No");
				try {
					String s = scanner.nextLine();
					loop = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					System.out.println("Introduce 0 o 1");
				}
			}
		}
	}

	private static void consultEst() {
		for (int i = 0; i < estancias.size(); i++) {
			System.out.println("Codigo estancia: " + estancias.get(i).getCodEstancia());
			System.out.println("Precio pagado: " + estancias.get(i).getPrecioPagado());
			System.out.println("Fecha inicio: " + estancias.get(i).getFechaInicio());
			System.out.println("Fecha fin: " + estancias.get(i).getFechaFin());
			System.out.println("Nombre estudiante " + estancias.get(i).getCodEstudiante().getNomEstudiante());
			System.out.println("Nombre residencia " + estancias.get(i).getCodResidencia().getNomResidencia());
			System.out.println("Nombre universidad " + estancias.get(i).getCodResidencia().getCodUniversidad().getNomUniversidad());
			System.out.println("----------------");
		}
	}

	private static void actualiEst() {
		int anno = -1;
		int mes = -1;
		int dia = -1;
		
		int codEsta = -1;
		String codEstaS = "";
		Scanner scanner = new Scanner(System.in);
		while (!codigosEsta.contains(codEsta)) {
			System.out.println("Introduzca un codigo de estancia a actualizar, los existentes son: ");
			for (int j = 0; j < codigosEsta.size(); j++) {
				System.out.println("Estancia Codigo: "+ codigosEsta.get(j) + " del alumno: "+ estuEsta.get(j).getNomEstudiante() + 
						" en la residencia: " + resiEsta.get(j).getNomResidencia());
			}
			codEstaS = scanner.nextLine();
			try {
				codEsta = Integer.parseInt(codEstaS);
			} catch (NumberFormatException e) {
			}
		}
		
		int codEstu = -1;
		String codEstS = "";
		
		while (!codigosEstu.contains(codEstu)) {
			System.out.println("Introduzca el nuevo codigo de estudiante , los existentes son: ");
			for (int j = 0; j < codigosUni.size(); j++) {
				System.out.println(nombresEstu.get(j) + ", Codigo: " + codigosEstu.get(j));
			}
			codEstS = scanner.nextLine();
			try {
				codEstu = Integer.parseInt(codEstS);
			} catch (NumberFormatException e) {
			}
		}
		int codResi = -1;
		String codResiS = "";
		while (!codigosResi.contains(codResi)) {
			System.out.println("Introduzca un nuevo codigo de residencia, los existentes son: ");
			for (int j = 0; j < codigosResi.size(); j++) {
				System.out.println(nombresResi.get(j) + ", Codigo: " + codigosResi.get(j));
			}
			codResiS = scanner.nextLine();
			try {
				codResi = Integer.parseInt(codResiS);
			} catch (NumberFormatException e) {
			}
		}
		int precio = -1;
		String precioS = "";
		while (precio < 0) {
			System.out.println("Introduzca un nuevo precio pagado por la estancia");
			precioS = scanner.nextLine();
			try {
				precio = Integer.parseInt(precioS);
			} catch (NumberFormatException e) {
			}
		}
		String annoS = "";
		while (anno < 0 || anno > Calendar.getInstance().get(Calendar.YEAR)) {
			System.out.println("Introduzca un nuevo año de inicio: ");
			annoS = scanner.nextLine();
			try {
				anno = Integer.parseInt(annoS);
			} catch (NumberFormatException e) {
			}
		}
		String mesS = "";
		while (mes < 1 || mes > 12) {
			System.out.println("Introduzca un nuevo mes de inicio: ");
			mesS = scanner.nextLine();
			try {
				mes = Integer.parseInt(mesS);
			} catch (NumberFormatException e) {
			}
		}
		String diaS = "";
		while (dia < 1 || dia > 31) {
			System.out.println("Introduzca un nuevo dia de inicio: ");
			diaS = scanner.nextLine();
			try {
				dia = Integer.parseInt(diaS);
			} catch (NumberFormatException e) {
			}
		}
		Date inicio = new Date(anno, mes, dia);
		annoS = "";
		while (anno < 0 || anno > Calendar.getInstance().get(Calendar.YEAR)) {
			System.out.println("Introduzca un nuevo año de fin: ");
			annoS = scanner.nextLine();
			try {
				anno = Integer.parseInt(annoS);
			} catch (NumberFormatException e) {
			}
		}
		mesS = "";
		while (mes < 1 || mes > 12) {
			System.out.println("Introduzca un nuevo mes de fin: ");
			mesS = scanner.nextLine();
			try {
				mes = Integer.parseInt(mesS);
			} catch (NumberFormatException e) {
			}
		}
		diaS = "";
		while (dia < 1 || dia > 31) {
			System.out.println("Introduzca un nuevo dia de fin: ");
			diaS = scanner.nextLine();
			try {
				dia = Integer.parseInt(diaS);
			} catch (NumberFormatException e) {
			}
		}
		Date fin = new Date(anno, mes, dia);

		Session update = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = update.beginTransaction();
		Query q = update.createQuery("update Estancias set codResidencia = :codres , codEstudiante = :codestu "
				+ ", fechaInicio = :fechainicio, precioPagado = :preciopagado, fechaFin = :fechaFin where codEsta = :codesta ");
		q.setInteger("codres", codResi);
		q.setInteger("codestu", codEstu);
		q.setDate("fechainicio", inicio);
		q.setDate("fechafin", fin);
		q.setInteger("codesta", codEsta);
		q.executeUpdate();

		update.close();

	}

	private static void eliminEst() {
		int codEsta = -1;
		String codEstaS = "";
		Scanner scanner = new Scanner(System.in);
		while (!codigosEsta.contains(codEsta)) {
			System.out.println("Introduzca un codigo de estancia a eliminar, los existentes son: ");
			for (int j = 0; j < codigosEsta.size(); j++) {
				System.out.println("Estancia Codigo: "+ codigosEsta.get(j) + " del alumno: "+ estuEsta.get(j).getNomEstudiante() + 
						" en la residencia: " + resiEsta.get(j).getNomResidencia());
			}
			codEstaS = scanner.nextLine();
			try {
				codEsta = Integer.parseInt(codEstaS);
			} catch (NumberFormatException e) {
			}
		}
		Session delete = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = delete.beginTransaction();
		Query q = delete.createQuery("delete from Estancias where codEstancia = :id ");
		q.setInteger("id", codEsta);
		q.executeUpdate();
		System.out.println("Valor borrado");

		delete.close();
	}

	private static void insertEst() {
		int anno = -1;
		int mes = -1;
		int dia = -1;

		int codEstu = -1;
		String codEstS = "";
		Scanner scanner = new Scanner(System.in);
		while (!codigosEstu.contains(codEstu)) {
			System.out.println("Introduzca un codigo de estudiante existente, los existentes son: ");
			for (int j = 0; j < codigosUni.size(); j++) {
				System.out.println(nombresEstu.get(j) + ", Codigo: " + codigosEstu.get(j));
			}
			codEstS = scanner.nextLine();
			try {
				codEstu = Integer.parseInt(codEstS);
			} catch (NumberFormatException e) {
			}
		}
		int codResi = -1;
		String codResiS = "";
		while (!codigosResi.contains(codResi)) {
			System.out.println("Introduzca un codigo de residencia existente, los existentes son: ");
			for (int j = 0; j < codigosResi.size(); j++) {
				System.out.println(nombresResi.get(j) + ", Codigo: " + codigosResi.get(j));
			}
			codResiS = scanner.nextLine();
			try {
				codResi = Integer.parseInt(codResiS);
			} catch (NumberFormatException e) {
			}
		}
		int precio = -1;
		String precioS = "";
		while (precio < 0) {
			System.out.println("Introduzca un precio pagado por la estancia");
			precioS = scanner.nextLine();
			try {
				precio = Integer.parseInt(precioS);
			} catch (NumberFormatException e) {
			}
		}
		String annoS = "";
		while (anno < 0 || anno > Calendar.getInstance().get(Calendar.YEAR)) {
			System.out.println("Introduzca un año de inicio: ");
			annoS = scanner.nextLine();
			try {
				anno = Integer.parseInt(annoS);
			} catch (NumberFormatException e) {
			}
		}
		String mesS = "";
		while (mes < 1 || mes > 12) {
			System.out.println("Introduzca un mes de inicio: ");
			mesS = scanner.nextLine();
			try {
				mes = Integer.parseInt(mesS);
			} catch (NumberFormatException e) {
			}
		}
		String diaS = "";
		while (dia < 1 || dia > 31) {
			System.out.println("Introduzca un dia de inicio: ");
			diaS = scanner.nextLine();
			try {
				dia = Integer.parseInt(diaS);
			} catch (NumberFormatException e) {
			}
		}
		Date inicio = new Date(anno, mes, dia);
		annoS = "";
		while (anno < 0 || anno > Calendar.getInstance().get(Calendar.YEAR)) {
			System.out.println("Introduzca un año de fin: ");
			annoS = scanner.nextLine();
			try {
				anno = Integer.parseInt(annoS);
			} catch (NumberFormatException e) {
			}
		}
		mesS = "";
		while (mes < 1 || mes > 12) {
			System.out.println("Introduzca un mes de fin: ");
			mesS = scanner.nextLine();
			try {
				mes = Integer.parseInt(mesS);
			} catch (NumberFormatException e) {
			}
		}
		diaS = "";
		while (dia < 1 || dia > 31) {
			System.out.println("Introduzca un dia de fin: ");
			diaS = scanner.nextLine();
			try {
				dia = Integer.parseInt(diaS);
			} catch (NumberFormatException e) {
			}
		}
		Date fin = new Date(anno, mes, dia);

		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = sesion.beginTransaction();
		Estudiantes estudiante = new Estudiantes();
		Residencias residencia = new Residencias();
		Estancias estancia = new Estancias();
		for (int j = 0; j < estudiantes.size(); j++) {
			if (estudiantes.get(j).getCodEstudiante() == codEstu) {
				estudiante = estudiantes.get(j);
			}
		}
		for (int j = 0; j < residencias.size(); j++) {
			if (residencias.get(j).getCodResidencia() == codResi) {
				residencia = residencias.get(j);
			}
		}
		estancia.setCodResidencia(residencia);
		estancia.setCodEstudiante(estudiante);
		estancia.setFechaFin(fin);
		estancia.setFechaInicio(inicio);
		estancia.setPrecioPagado(precio);
		sesion.save(estancia);
		trans.commit();
		sesion.close();

	}

	private static void consultRes() {
		for (int i = 0; i < residencias.size(); i++) {
			System.out.println("Codigo Residencia: " + residencias.get(i).getCodResidencia());
			System.out.println("Nombre Residencia: " + residencias.get(i).getNomResidencia());
			System.out.println("Codigo Universidad: " + residencias.get(i).getCodUniversidad().getCodUniversidad());
			System.out.println("Nombre Universidad: " + residencias.get(i).getCodUniversidad().getNomUniversidad());
			System.out.println("Comedor: " + residencias.get(i).getComedor());
			System.out.println("Precio Mensual: " + residencias.get(i).getPrecioMensual());
			if (codigosResiOb.contains(residencias.get(i).getCodResidencia())){
				System.out.println("Observaciones: " + observacionesText.get(codigosResiOb.indexOf(residencias.get(i).getCodResidencia())));
			}
			System.out.println("----------------");
		}
	}

	private static void actualiRes() {
		int codResi = -1;
		String codResiS = "";
		Scanner scanner = new Scanner(System.in);
		while (!codigosResi.contains(codResi)) {
			System.out.println("Introduzca un codigo de residencia existente, los existentes son: ");
			for (int j = 0; j < codigosResi.size(); j++) {
				System.out.println(nombresResi.get(j) + ", Codigo: " + codigosResi.get(j));
			}
			codResiS = scanner.nextLine();
			try {
				codResi = Integer.parseInt(codResiS);
			} catch (NumberFormatException e) {
			}
		}
		String codUni = "";
		scanner = new Scanner(System.in);
		while (!codigosUni.contains(codUni)) {
			System.out.println("Introduzca un nuevo codigo de universidad existente, los existentes son: ");
			for (int j = 0; j < codigosUni.size(); j++) {
				System.out.println(nombresUni.get(j) + ", Codigo: " + codigosUni.get(j));
			}
			codUni = scanner.nextLine();
		}
		String comedor = "";
		int comedorBool = -1;
		while (comedorBool < 0 || comedorBool > 1) {
			System.out.println("Introduzca 1 si tiene comedor o 0 si no lo tiene");
			try {
				comedor = scanner.nextLine();
				comedorBool = Integer.parseInt(comedor);
			} catch (NumberFormatException e) {
				System.out.println("Introduce 0 o 1");
			}
		}
		System.out.println("Introduzca un nuevo nombre para la residencia");
		String nomRes = scanner.nextLine();
		int precio = -1;
		while (precio < 0) {
			System.out.println("Introduzca un nuevo precio para la residencia");
			String precioS = scanner.nextLine();
			try {
				precio = Integer.parseInt(precioS);
			} catch (NumberFormatException e) {
			}
		}
		int i = -1;
		while (i < 0 || i > 2) {
			System.out.println("Indique si desea introducir observacion: 1 = Si, 2 = No");
			try {
				String s = scanner.nextLine();
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Introduce 1 o 2");
			}
		}
		String observacion = "";
		if (i == 1) {
			System.out.println("Introduzca observacion: ");
			observacion = scanner.nextLine();
		}
		Session update = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = update.beginTransaction();
		Query q = update.createQuery("update Residencias set codUniversidad = :coduni , Comedor = :comedor "
				+ ", nomResidencia = :nomres, precioMensual = :preciomes where codResidencia = :codres ");
		q.setInteger("codres", codResi);
		q.setString("coduni", codUni);
		q.setInteger("comedor", comedorBool);
		q.setString("nomres", nomRes);
		q.setInteger("preciomes", precio);
		q.executeUpdate();

		update.close();

	}

	private static void eliminRes() {
		int codResi = -1;
		String codResiS = "";
		Scanner scanner = new Scanner(System.in);
		while (!codigosResi.contains(codResi)) {
			System.out.println("Introduzca un codigo de residencia existente, los existentes son: ");
			for (int j = 0; j < codigosResi.size(); j++) {
				System.out.println(nombresResi.get(j) + ", Codigo: " + codigosResi.get(j));
			}
			codResiS = scanner.nextLine();
			try {
				codResi = Integer.parseInt(codResiS);
			} catch (NumberFormatException e) {
			}
		}
		Session delete = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = delete.beginTransaction();
		Query q = delete.createQuery("delete from Residencias where codResidencia = :id ");
		q.setInteger("id", codResi);
		q.executeUpdate();
		System.out.println("Valor borrado");

		delete.close();

	}

	private static void crearBase() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = sesion.beginTransaction();
		Residencias residencia = new Residencias();
		Residencias residencia2 = new Residencias();
		Estudiantes estudiante = new Estudiantes();
		Estudiantes estudiante2 = new Estudiantes();
		Estancias estancia = new Estancias();
		Estancias estancia2 = new Estancias();
		ResidenciasObservaciones resiob = new ResidenciasObservaciones();
		ResidenciasObservaciones resiob2 = new ResidenciasObservaciones();
		Universidades universidad = new Universidades();
		Universidades universidad2 = new Universidades();

		estudiante.setDni("111111111");
		estudiante.setNomEstudiante("Juan");
		estudiante.setTelefonoEstudiante("111111111");
		sesion.save(estudiante);

		estudiante2.setDni("222222222");
		estudiante2.setNomEstudiante("Pepe");
		estudiante2.setTelefonoEstudiante("222222222");
		sesion.save(estudiante2);

		universidad.setCodUniversidad("111111");
		universidad.setNomUniversidad("Universidad 1");
		sesion.save(universidad);

		residencia.setCodUniversidad(universidad);
		residencia.setComedor(1);
		residencia.setNomResidencia("Residencia 1");
		residencia.setPrecioMensual(1000);
		sesion.save(residencia);

		estancia.setCodResidencia(residencia);
		estancia.setCodEstudiante(estudiante);
		estancia.setFechaFin(new Date(2013, 3, 4));
		estancia.setFechaInicio(new Date(2011, 3, 4));
		estancia.setPrecioPagado(1200);
		sesion.save(estancia);

		resiob.setCodFResidencia(residencia);
		resiob.setObservaciones("Es mas cara que la otra residencia");
		sesion.save(resiob);

		universidad2.setCodUniversidad("222222");
		universidad2.setNomUniversidad("Universidad 2");
		sesion.save(universidad2);

		residencia2.setCodUniversidad(universidad2);
		residencia2.setComedor(0);
		residencia2.setNomResidencia("Residencia 2");
		residencia2.setPrecioMensual(1200);
		sesion.save(residencia2);

		estancia2.setCodResidencia(residencia2);
		estancia2.setCodEstudiante(estudiante2);
		estancia2.setFechaFin(new Date(2013, 4, 4));
		estancia2.setFechaInicio(new Date(2012, 4, 4));
		estancia2.setPrecioPagado(1000);
		sesion.save(estancia2);

		resiob2.setCodFResidencia(residencia2);
		resiob2.setObservaciones("La piscina esta guapa pero hay que cerrar" + " el balcon porque se mato un guiri");
		sesion.save(resiob2);

		trans.commit();
		sesion.close();

	}

	private static void insertRes() {
		String codUni = "";
		Scanner scanner = new Scanner(System.in);
		while (!codigosUni.contains(codUni)) {
			System.out.println("Introduzca un codigo de universidad existente, los existentes son: ");
			for (int j = 0; j < codigosUni.size(); j++) {
				System.out.println(nombresUni.get(j) + ", Codigo: " + codigosUni.get(j));
			}
			codUni = scanner.nextLine();
		}
		String comedor = "";
		int comedorBool = -1;
		while (comedorBool < 0 || comedorBool > 1) {
			System.out.println("Introduzca 1 si tiene comedor o 0 si no lo tiene");
			try {
				comedor = scanner.nextLine();
				comedorBool = Integer.parseInt(comedor);
			} catch (NumberFormatException e) {
				System.out.println("Introduce 0 o 1");
			}
		}
		System.out.println("Introduzca un nombre para la residencia");
		String nomRes = scanner.nextLine();
		int precio = -1;
		while (precio < 0) {
			System.out.println("Introduzca un precio para la residencia");
			String precioS = scanner.nextLine();
			try {
				precio = Integer.parseInt(precioS);
			} catch (NumberFormatException e) {
			}
		}
		int i = -1;
		while (i < 0 || i > 2) {
			System.out.println("Indique si desea introducir observacion: 1 = Si, 2 = No");
			try {
				String s = scanner.nextLine();
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Introduce 1 o 2");
			}
		}
		String observacion = "";
		if (i == 1) {
			System.out.println("Introduzca observacion: ");
			observacion = scanner.nextLine();
		}
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = sesion.beginTransaction();
		Universidades universidad = new Universidades();
		for (int j = 0; j < universidades.size(); j++) {
			if (universidades.get(j).getCodUniversidad() == codUni) {
				universidad = universidades.get(j);
			}
		}

		Residencias residencia = new Residencias();
		ResidenciasObservaciones resiob = new ResidenciasObservaciones();

		residencia.setCodUniversidad(universidad);
		residencia.setComedor(comedorBool);
		residencia.setNomResidencia(nomRes);
		residencia.setPrecioMensual(precio);

		sesion.save(residencia);

		if (i == 1) {
			resiob.setCodFResidencia(residencia);
			resiob.setObservaciones(observacion);
			sesion.save(resiob);
		}

		trans.commit();
		sesion.close();

	}

}
