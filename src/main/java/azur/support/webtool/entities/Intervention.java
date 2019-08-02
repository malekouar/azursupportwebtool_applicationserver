package azur.support.webtool.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the intervention database table.
 * 
 */
@Entity
//@NamedQuery(name="Intervention.findAll", query="SELECT i FROM Intervention i")
@Table(name = "intervention")
public class Intervention {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_debut")
	private Date dateDebut;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_fin")
	private Date dateFin;

	private String description;

	@Lob
	private String detail;

	private String responsable;

	private String type;

	//bi-directional many-to-one association to Dossier
	@ManyToOne
	private Dossier dossier;

	//bi-directional many-to-one association to Etat
	@ManyToOne
	private Etat etat;

	//bi-directional many-to-one association to Livraison
	@OneToMany(mappedBy="intervention")
	private List<Livraison> livraisons;

	public Intervention() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Dossier getDossier() {
		return this.dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	public Etat getEtat() {
		return this.etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public List<Livraison> getLivraisons() {
		return this.livraisons;
	}

	public void setLivraisons(List<Livraison> livraisons) {
		this.livraisons = livraisons;
	}

	public Livraison addLivraison(Livraison livraison) {
		getLivraisons().add(livraison);
		livraison.setIntervention(this);

		return livraison;
	}

	public Livraison removeLivraison(Livraison livraison) {
		getLivraisons().remove(livraison);
		livraison.setIntervention(null);

		return livraison;
	}

}