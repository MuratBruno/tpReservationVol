package dtaIngenierie.tpReservationVol.modele;


public enum TypeAvion {
	A330,A340,A380,B747;
	
	static TypeAvion  getTypeAvion(String s) throws java.lang.Exception{
		switch(s) {
			case "A330": return A330;
			case "A340": return A340;
			case "A380": return A380;
			case "B747": return B747;
			default : throw new Exception("Erreur");
		}
	}
	/* TypeAvion  getTypeAvion2(String s) throws java.lang.Exception{
		if(s==this.name()) {
			return this;
		}
	}*/
	
}
