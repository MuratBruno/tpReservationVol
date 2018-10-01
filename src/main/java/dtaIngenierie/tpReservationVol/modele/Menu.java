package dtaIngenierie.tpReservationVol.modele;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import dtaIngenirie.tpReservationVol.DAO.DatabaseHelper;

public abstract class Menu {
	public static void afficheMenu() {
		boolean exit = false;
		Scanner sc = new Scanner(System.in);
		while (!exit) {
			System.out.println("Reservation de vol menu principal");
			System.out.println("1) Gestion des vols");
			System.out.println("2) Gestion des reservations");
			System.out.println("3) Quitter");

			int choix = Integer.parseInt(sc.nextLine());

			if (choix == 1) {
				try {
					System.out.println("Gestion des vol");
					System.out.println("1) Creation vols");
					System.out.println("2) Liste des vols");
					System.out.println("3) Rechercher un avion par numéro");
					System.out.println("4) Rechercher un avion par ville de départ et d'arrivée");
					int choix2 = Integer.parseInt(sc.nextLine());
					switch (choix2) {
					case 1:
						try {
							creerVol(sc);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 2:
						listeVols();
						break;
					case 3:
						affichageTrouverVolParNum(sc);
						break;

					case 4:
						affichageTrouverVolParVille(sc);
						break;
					}
				} catch (javax.persistence.NoResultException e) {
					// TODO: handle exception
					reservationException e2 = new reservationException(e);
					e2.afficheVolIntrouve();				}
			} else if (choix == 2) {
				try {
					System.out.println("Gestion des reservations");
					System.out.println("1) Créer une reservation");
					System.out.println("2) Voir les réservations d'un vol");
					System.out.println("3) Annuler une réservation");
					System.out.println("4) Voir toutes les réservations d'une personne");
					int choix2 = Integer.parseInt(sc.nextLine());
					switch (choix2) {
					case 1:
						creerReservation(sc);
						break;
					case 2:
						affichageTrouverReservationParVol(sc);
						break;
					case 3:
						afficheDeleteReservation(sc);
						break;
					case  4: afficherReservationsParPersonne(sc);break;
					}
				} catch (javax.persistence.NoResultException e) {
					// TODO: handle exception
					reservationException e2 = new reservationException(e);
					e2.afficheReservationIntrouve();
				}
			} else {
				exit = true;
			}

		}
		sc.close();
		System.exit(1);
	}

	public static void creerReservation(Scanner sc) {
		System.out.println("Numéro de vol : ");
		String numVol = sc.nextLine();
		Vol vol = Service.trouverVolParNum(numVol);

		System.out.println("Nom : ");
		String nom = sc.nextLine();
		System.out.println("Prénom : ");
		String prenom = sc.nextLine();
		System.out.println("age : ");
		Integer age = Integer.parseInt(sc.nextLine());

		Reservation r = new Reservation(nom, prenom, age, vol);
		Service.insertReservation(r);

		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		r = em.find(Reservation.class, r.getId());
		r.setNum(numVol + "-" + r.getId());
		em.merge(r);
		DatabaseHelper.commitTxAndClose(em);

	}

	public static void listeVols() {
		List<Vol> lv = Service.listeVols();
		Service.afficheVols(lv);
	}

	public static void creerVol(Scanner sc) throws Exception {
		System.out.println("Numéro de vol : ");
		String numVol = sc.nextLine();

		System.out.println("Type d'avion : ");
		String typeAvion = sc.nextLine();
		TypeAvion t = TypeAvion.getTypeAvion(typeAvion);

		System.out.println("Nombre de place : ");
		Integer nbPlace = Integer.parseInt(sc.nextLine());
		System.out.println("Ville de départ : ");
		String villeDepart = sc.nextLine();
		System.out.println("Ville d'arrivée : ");
		String villeArrive = sc.nextLine();
		System.out.println("Date de départ (format dd/MM/yyyy) : ");
		/*
		 * System.out.println("Jour: "); int j= sc.nextInt();
		 * 
		 * System.out.println("Mois: "); int m= sc.nextInt();
		 * 
		 * System.out.println("Année: "); int a= sc.nextInt();
		 */
		String sDate = sc.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse(sDate);

		Vol v = new Vol(numVol, t, nbPlace, villeDepart, villeArrive, d);
		Service.insertVol(v);

	}

	public static void affichageTrouverVolParNum(Scanner sc) {
		System.out.println("Numéro de vol : ");
		String numVol = sc.nextLine();

		Vol v = Service.trouverVolParNum(numVol);
		String s = String.format("%-10s | %-10s| %-10s| %-10s| %-10s| %-10s", "Numéro", "Type", "Place", "Départ",
				"Arrivé", "Date");
		System.out.println(s);
		System.out.println(v);
	}

	public static void affichageTrouverVolParVille(Scanner sc) {
		System.out.println("Départ: ");
		String depart = sc.nextLine();

		System.out.println("Départ: ");
		String arrive = sc.nextLine();

		Vol v = Service.trouverVolParVille(depart, arrive);
		String s = String.format("%-10s | %-10s| %-10s| %-10s| %-10s| %-10s", "Numéro", "Type", "Place", "Départ",
				"Arrivé", "Date");
		System.out.println(s);
		System.out.println(v);
	}

	public static void affichageTrouverReservationParVol(Scanner sc) {
		System.out.println("Numéro de vol : ");
		String numVol = sc.nextLine();

		Vol v = Service.trouverVolParNum(numVol);
		List<Reservation> lv = Service.reservationsDuVol(v);
		String s = String.format("%-10s | %-10s| %-10s", "Nom", "Prénom", "Age");
		System.out.println(s);
		Service.afficheReservations(lv);
	}

	public static void afficheDeleteReservation(Scanner sc) {
		System.out.println("Numéro de reservation : ");
		String numRes = sc.nextLine();

		Service.deleteReservation(numRes);
		System.out.println("Reservation supprimée");
	}
	
	public static void afficherReservationsParPersonne(Scanner sc) {
		System.out.println("Nom : ");
		String nom = sc.nextLine();


		List<Reservation> lv = Service.reservationsParPersonne(nom);
		String s = String.format("%-10s |%-10s | %-10s| %-10s","Num", "Nom", "Prénom", "Age");
		System.out.println(s);
		Service.afficheReservations(lv);
	}
}
