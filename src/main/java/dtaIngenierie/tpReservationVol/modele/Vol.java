package dtaIngenierie.tpReservationVol.modele;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vol")
public class Vol {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true)
	private String num;
	
	@Enumerated(EnumType.STRING)
	private TypeAvion typeAvion;
	private int NbPlace;
	private String villeDepart;
	private String villeArrive;
	
	private Date dateDepart;

	@OneToMany(mappedBy="vol")
	private List<Reservation> reservations;

	

	public Vol() {
		
	}


	public Vol(String numVol,TypeAvion typeAvion, Integer nbPlace, String villeDepart, String villeArrive, Date d) {
		// TODO Auto-generated constructor stub
		super();
		this.num=numVol;
		this.typeAvion = typeAvion;
		NbPlace = nbPlace;
		this.villeDepart = villeDepart;
		this.villeArrive = villeArrive;
		this.dateDepart = d;

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

	public TypeAvion getTypeAvion() {
		return typeAvion;
	}

	public void setTypeAvion(TypeAvion typeAvion) {
		this.typeAvion = typeAvion;
	}

	public int getNbPlace() {
		return NbPlace;
	}

	public void setNbPlace(int nbPlace) {
		NbPlace = nbPlace;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrive() {
		return villeArrive;
	}

	public void setVilleArrive(String villeArrive) {
		this.villeArrive = villeArrive;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		int n = Service.reservationsDuVol(this).size();
		return String.format("%-10s | %-10s| %-10s| %-10s| %-10s| %-10s", num , typeAvion , n+"/"+NbPlace ,
				 villeDepart , villeArrive , dateDepart );

	}
	
	
	
	
//	
}
