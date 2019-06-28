package com.example.tutomvcjpa.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the dossier database table.
 * 
 */
@Entity
//@NamedQuery(name="Dossier.findAll", query="SELECT d FROM Dossier d")
@Table(name = "dossier")
public class Dossier {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name="date_debut")
	private Date dateDebut;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_fin")
	private Date dateFin;

	@NotBlank(message = "Responsable obligatoire")
	private String responsable;

	@NotBlank(message = "type obligatoire")
	private String type;

	@Column(name="url_akuiteo")
	private String urlAkuiteo;

	@Column(name="url_azimut")
	private String urlAzimut;

	@Column(name="url_redmine")
	private String urlRedmine;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to Etat
	@ManyToOne
	private Etat etat;

	//bi-directional many-to-one association to Intervention
	@OneToMany(mappedBy="dossier")
	private List<Intervention> interventions;

	public Dossier() {
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

	public String getUrlAkuiteo() {
		return this.urlAkuiteo;
	}

	public void setUrlAkuiteo(String urlAkuiteo) {
		this.urlAkuiteo = urlAkuiteo;
	}

	public String getUrlAzimut() {
		return this.urlAzimut;
	}

	public void setUrlAzimut(String urlAzimut) {
		this.urlAzimut = urlAzimut;
	}

	public String getUrlRedmine() {
		return this.urlRedmine;
	}

	public void setUrlRedmine(String urlRedmine) {
		this.urlRedmine = urlRedmine;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Etat getEtat() {
		return this.etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public List<Intervention> getInterventions() {
		return this.interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}

	public Intervention addIntervention(Intervention intervention) {
		getInterventions().add(intervention);
		intervention.setDossier(this);

		return intervention;
	}

	public Intervention removeIntervention(Intervention intervention) {
		getInterventions().remove(intervention);
		intervention.setDossier(null);

		return intervention;
	}

}