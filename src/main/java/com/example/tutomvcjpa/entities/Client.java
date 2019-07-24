package com.example.tutomvcjpa.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
//@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
@Table(name = "client")
public class Client {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Contact obligatoire")
	private String contact;

	private String email;

	private String mobile;

	@NotBlank(message = "Nom obligatoire")
	private String raisonSociale;

	@NotBlank(message = "Tel obligatoire")	
	private String tel;

	//bi-directional many-to-one association to Dossier
	@OneToMany(mappedBy="client")
	private List<Dossier> dossiers;

	//bi-directional many-to-one association to Serveur
	@OneToMany(mappedBy="client")
	private List<Serveur> serveurs;	

	public Client() {
	}
	

	public Client(int id, @NotBlank(message = "Contact obligatoire") String contact, String email, String mobile,
			@NotBlank(message = "Nom obligatoire") String raisonSociale,
			@NotBlank(message = "Tel obligatoire") String tel) {
		super();
		this.id = id;
		this.contact = contact;
		this.email = email;
		this.mobile = mobile;
		this.raisonSociale = raisonSociale;
		this.tel = tel;
	}



	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRaisonSociale() {
		return this.raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Dossier> getDossiers() {
		return this.dossiers;
	}

	public void setDossiers(List<Dossier> dossiers) {
		this.dossiers = dossiers;
	}

	public Dossier addDossier(Dossier dossier) {
		getDossiers().add(dossier);
		dossier.setClient(this);

		return dossier;
	}

	public Dossier removeDossier(Dossier dossier) {
		getDossiers().remove(dossier);
		dossier.setClient(null);

		return dossier;
	}
	
	public List<Serveur> getServeurs() {
		return this.serveurs;
	}

	public void setServeurs(List<Serveur> serveurs) {
		this.serveurs = serveurs;
	}

	public Serveur addServeur(Serveur serveur) {
		getServeurs().add(serveur);
		serveur.setClient(this);

		return serveur;
	}

	public Serveur removeServeur(Serveur serveur) {
		getServeurs().remove(serveur);
		serveur.setClient(null);

		return serveur;
	}	

	@Override
	public String toString() {
		return "Client [id=" + id + ", contact=" + contact + ", email=" + email + ", mobile=" + mobile
				+ ", raisonSociale=" + raisonSociale + ", tel=" + tel + ", dossiers=" + dossiers + ", serveurs=" + serveurs
				+ "]";
	}

}