package com.example.tutomvcjpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 * The persistent class for the serveur database table.
 * 
 */
@Entity
//@NamedQuery(name="Serveur.findAll", query="SELECT s FROM Serveur s")
@Table (name = "serveur")
public class Serveur {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Login obligatoire")
	private String login;

	@NotBlank(message = "Mot de passe obligatoire")
	private String password;

	@NotBlank(message = "IP obligatoire")
	@Column(name="serveur_ip")
	private String serveurIp;

	@NotBlank(message = "Nom obligatoire")	
	@Column(name="serveur_nom")
	private String serveurNom;

	@NotBlank(message = "Type obligatoire (SQL, WF, GED, CAPTURE ...)")
	@Column(name="serveur_type")
	private String serveurType;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;
	
	//bi-directional many-to-one association to Config
//	@ManyToOne
//	private Config config;

	public Serveur() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServeurIp() {
		return this.serveurIp;
	}

	public void setServeurIp(String serveurIp) {
		this.serveurIp = serveurIp;
	}

	public String getServeurNom() {
		return this.serveurNom;
	}

	public void setServeurNom(String serveurNom) {
		this.serveurNom = serveurNom;
	}

	public String getServeurType() {
		return this.serveurType;
	}

	public void setServeurType(String serveurType) {
		this.serveurType = serveurType;
	}

//	public Config getConfig() {
//		return this.config;
//	}
//
//	public void setConfig(Config config) {
//		this.config = config;
//	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}