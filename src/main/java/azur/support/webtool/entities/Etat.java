package azur.support.webtool.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 * The persistent class for the etat database table.
 * 
 */
@Entity
//@NamedQuery(name="Etat.findAll", query="SELECT e FROM Etat e")
@Table(name = "etat")
public class Etat {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Libellé obligatoire")
	private String libelle;

	//bi-directional many-to-one association to Dossier
	@OneToMany(mappedBy="etat")
	private List<Dossier> dossiers;

	//bi-directional many-to-one association to Intervention
	@OneToMany(mappedBy="etat")
	private List<Intervention> interventions;

	//bi-directional many-to-one association to Livraison
	@OneToMany(mappedBy="etat")
	private List<Livraison> livraisons;

	public Etat() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Dossier> getDossiers() {
		return this.dossiers;
	}

	public void setDossiers(List<Dossier> dossiers) {
		this.dossiers = dossiers;
	}

	public Dossier addDossier(Dossier dossier) {
		getDossiers().add(dossier);
		dossier.setEtat(this);

		return dossier;
	}

	public Dossier removeDossier(Dossier dossier) {
		getDossiers().remove(dossier);
		dossier.setEtat(null);

		return dossier;
	}

	public List<Intervention> getInterventions() {
		return this.interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}

	public Intervention addIntervention(Intervention intervention) {
		getInterventions().add(intervention);
		intervention.setEtat(this);

		return intervention;
	}

	public Intervention removeIntervention(Intervention intervention) {
		getInterventions().remove(intervention);
		intervention.setEtat(null);

		return intervention;
	}

	public List<Livraison> getLivraisons() {
		return this.livraisons;
	}

	public void setLivraisons(List<Livraison> livraisons) {
		this.livraisons = livraisons;
	}

	public Livraison addLivraison(Livraison livraison) {
		getLivraisons().add(livraison);
		livraison.setEtat(this);

		return livraison;
	}

	public Livraison removeLivraison(Livraison livraison) {
		getLivraisons().remove(livraison);
		livraison.setEtat(null);

		return livraison;
	}

}