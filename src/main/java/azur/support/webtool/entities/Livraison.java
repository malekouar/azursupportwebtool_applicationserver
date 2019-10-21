package azur.support.webtool.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the livraisons database table.
 * 
 */
@Entity
@Table(name="livraisons")
//@NamedQuery(name="Livraison.findAll", query="SELECT l FROM Livraison l")
public class Livraison {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")	
	@Column(name="date_livraison")
	private Date dateLivraison;

	@NotBlank(message = "Description obligatoire")
	private String description;

	@Lob
	private String detail;

	@Column(name="id_svn")
	@NotNull(message = "Id SVN obligatoire")	
	private long idSvn;

	@Column(name="nom_package")
	@NotBlank(message = "Nom du package obligatoire")
	private String nomPackage;

	@NotBlank(message = "Responsable obligatoire")
	private String responsable;

	@NotBlank(message = "Type obligatoire")
	private String type;

	//bi-directional many-to-one association to Etat
	@ManyToOne
	private Etat etat;

	//bi-directional many-to-one association to Intervention
	@ManyToOne
	private Intervention intervention;

	public Livraison() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateLivraison() {
		return this.dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
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

	public long getIdSvn() {
		return this.idSvn;
	}

	public void setIdSvn(long idSvn) {
		this.idSvn = idSvn;
	}

	public String getNomPackage() {
		return this.nomPackage;
	}

	public void setNomPackage(String nomPackage) {
		this.nomPackage = nomPackage;
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

	public Etat getEtat() {
		return this.etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Intervention getIntervention() {
		return this.intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

}