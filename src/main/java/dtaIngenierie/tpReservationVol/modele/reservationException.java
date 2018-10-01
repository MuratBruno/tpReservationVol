package dtaIngenierie.tpReservationVol.modele;

public class reservationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4994391047998453015L;
	String messageAffiche="Ressource";
	javax.persistence.NoResultException e;
	public reservationException(javax.persistence.NoResultException e) {
		messageAffiche="Ressource";
		this.e=e;
	}
	
	
	
	public void affiche() {
		System.out.println("Erreur : "+messageAffiche+" introuvable !");
		System.out.println();
		System.out.println("Détails  ----------------------------------");
		e.printStackTrace();
	}
	public void afficheVolIntrouve() {

		
		System.out.println("Erreur : Vol introuvable !");
		System.out.println();
		System.out.println("Détails  ----------------------------------");
		e.printStackTrace();
	}
	
	public void afficheReservationIntrouve() {

		
		System.out.println("Erreur : Réservation introuvable !");
		System.out.println();
		System.out.println("Détails  ----------------------------------");
		e.printStackTrace();
	}
}
