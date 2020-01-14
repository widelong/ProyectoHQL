package basictest;

import org.hibernate.Session;

public class Test1 {

	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		TestClass1 test = new TestClass1();
		test.setNombre("Pepe2");
//		test.setId(2);
		sesion.save(test);
		sesion.getTransaction().commit();
		sesion.close();
		}
}

	

