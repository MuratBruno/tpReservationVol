package dtaIngenierie.tpReservationVol.modele;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import dtaIngenirie.tpReservationVol.DAO.DatabaseHelper;

@Repository
public class Service {

	public static void insertReservation(Reservation r) {

			EntityManager em = DatabaseHelper.createEntityManager();
			DatabaseHelper.beginTx(em);
			em.persist(r);

			DatabaseHelper.commitTxAndClose(em);
		
		
	}

	public static void insertVol(Vol v) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(v);

		DatabaseHelper.commitTxAndClose(em);
	}

	public static Vol trouverVolParNum(String numVol) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		TypedQuery<Vol> query = em.createQuery("select v " + "from Vol v " + "where v.num =:num ", Vol.class);
		query.setParameter("num", numVol);
		Vol v = query.getSingleResult();
		DatabaseHelper.commitTxAndClose(em);
		return v;

	}



	public static Reservation trouverReservation(String numRes) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		TypedQuery<Reservation> query = em.createQuery("select r " + "from Reservation r " + "where r.num =:num ",
				Reservation.class);
		query.setParameter("num", numRes);
		Reservation r = query.getSingleResult();
		DatabaseHelper.commitTxAndClose(em);
		return r;

	}

	public static List<Vol> listeVols() {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		TypedQuery<Vol> query = em.createQuery("select v from Vol v ", Vol.class);
		List<Vol> lv = query.getResultList();
		DatabaseHelper.commitTxAndClose(em);
		return lv;
	}

	public static void deleteReservation(String numRes) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		Reservation reservation = trouverReservation(numRes);
		em.remove(em.find(Reservation.class, reservation.getId()));
		

		DatabaseHelper.commitTxAndClose(em);
		
	}

	public static void afficheVols(List<Vol> lv) {
		String s =String.format("%-10s | %-10s| %-10s| %-10s| %-10s| %-10s", "Numéro" , "Type" , "Place",
				"Départ" , "Arrivé" , "Date" );
		System.out.println(s);
		for (Vol o : lv) {
			System.out.println(o.toString());
		}
	}

	public static void afficheReservations(List<Reservation> lr) {
		for (Reservation r : lr) {
			System.out.println(r.toString());
		}
	}

	public static List<Reservation> reservationsDuVol(Vol v) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		TypedQuery<Reservation> query = em.createQuery("select r from Reservation r where vol_id=:id ", Reservation.class);
		query.setParameter("id", v.getId());
		List<Reservation> lv = query.getResultList();
		DatabaseHelper.commitTxAndClose(em);
		return lv;
	}

	public static Vol trouverVolParVille(String depart, String arrive) {
		// TODO Auto-generated method stub
		
		  EntityManager em = DatabaseHelper.createEntityManager();
		  DatabaseHelper.beginTx(em);
		  
		  TypedQuery<Vol> query = em.createQuery("select v " + "from Vol v where villedepart=:depart and villearrive=:arrive " , Vol.class); 
		  query.setParameter("depart", depart); 
		  query.setParameter("arrive", arrive); 
		  Vol v = query.getSingleResult(); 
		  DatabaseHelper.commitTxAndClose(em);
		 
		return v;
	}
	
	public static List<Reservation> reservationsParPersonne(String nom) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		TypedQuery<Reservation> query = em.createQuery("select r from Reservation r where nom=:nom ", Reservation.class);
		query.setParameter("nom", nom);
		List<Reservation> lv = query.getResultList();
		DatabaseHelper.commitTxAndClose(em);
		return lv;
	}

}
