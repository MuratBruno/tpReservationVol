package dtaIngenirie.tpReservationVol;

import dtaIngenierie.tpReservationVol.modele.Menu;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		/*
		 * EntityManager em = DatabaseHelper.createEntityManager();
		 * DatabaseHelper.beginTx(em); //Menu.afficheMenu(); Vol v= new Vol();
		 * Reservation r = new Reservation(); r.setVol(v);
		 * 
		 * 
		 * em.persist(v); em.persist(r);
		 * 
		 * 
		 * DatabaseHelper.commitTxAndClose(em);
		 */
		
			Menu.afficheMenu();
		
	}
}
