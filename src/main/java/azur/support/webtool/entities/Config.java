package azur.support.webtool.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the config database table.
 * 
 */
@Entity
//@NamedQuery(name="Config.findAll", query="SELECT c FROM Config c")
@Table(name = "config")
public class Config {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="teamviewer_id")
	private String teamviewerId;

	@Column(name="teamviewer_password")
	private String teamviewerPassword;

	@Column(name="vpn_ip")
	private String vpnIp;

	@Column(name="vpn_login")
	private String vpnLogin;

	@Column(name="vpn_password")
	private String vpnPassword;

	@Column(name="vpn_type")
	private String vpnType;

	//bi-directional many-to-one association to Serveur
//	@OneToMany(mappedBy="config")
//	private List<Serveur> serveurs;

	//bi-directional one-to-one association to Client
	@OneToOne
	private Client client;

	public Config() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamviewerId() {
		return this.teamviewerId;
	}

	public void setTeamviewerId(String teamviewerId) {
		this.teamviewerId = teamviewerId;
	}

	public String getTeamviewerPassword() {
		return this.teamviewerPassword;
	}

	public void setTeamviewerPassword(String teamviewerPassword) {
		this.teamviewerPassword = teamviewerPassword;
	}

	public String getVpnIp() {
		return this.vpnIp;
	}

	public void setVpnIp(String vpnIp) {
		this.vpnIp = vpnIp;
	}

	public String getVpnLogin() {
		return this.vpnLogin;
	}

	public void setVpnLogin(String vpnLogin) {
		this.vpnLogin = vpnLogin;
	}

	public String getVpnPassword() {
		return this.vpnPassword;
	}

	public void setVpnPassword(String vpnPassword) {
		this.vpnPassword = vpnPassword;
	}

	public String getVpnType() {
		return this.vpnType;
	}

	public void setVpnType(String vpnType) {
		this.vpnType = vpnType;
	}

//	public List<Serveur> getServeurs() {
//		return this.serveurs;
//	}
//
//	public void setServeurs(List<Serveur> serveurs) {
//		this.serveurs = serveurs;
//	}

//	public Serveur addServeur(Serveur serveur) {
//		getServeurs().add(serveur);
//		serveur.setConfig(this);
//
//		return serveur;
//	}
//
//	public Serveur removeServeur(Serveur serveur) {
//		getServeurs().remove(serveur);
//		serveur.setConfig(null);
//
//		return serveur;
//	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", teamviewerId=" + teamviewerId + ", teamviewerPassword=" + teamviewerPassword
				+ ", vpnIp=" + vpnIp + ", vpnLogin=" + vpnLogin + ", vpnPassword=" + vpnPassword + ", vpnType="
				+ vpnType + ", client=" + client + "]";
	}

}