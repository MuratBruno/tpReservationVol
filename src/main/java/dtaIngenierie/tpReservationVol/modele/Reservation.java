package dtaIngenierie.tpReservationVol.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Reservation {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true)
	private String num;
	
	private String nom;
	private String prenom;
	private Integer age;
	

	@ManyToOne
	private Vol vol;

	public Reservation() {
		
	}



	public Reservation(String nom, String prenom, Integer age, Vol vol) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.vol = vol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	@Override
	public String toString() {
		return String.format("%-10s | %-10s| %-10s| %-10s",num  ,nom  ,prenom ,
				age   );
	}
	
	
}
