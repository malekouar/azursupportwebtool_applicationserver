package azur.support.webtool.forms;

import azur.support.webtool.entities.Client;
import azur.support.webtool.entities.Dossier;
import azur.support.webtool.entities.Etat;
import azur.support.webtool.entities.Intervention;
import azur.support.webtool.entities.Livraison;

public class LivraisonPackForm {
	
	private Client client;
	private Dossier dossier;
	private Intervention intervention;
	private Livraison livraison;
	private Etat etat;
	
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Dossier getDossier() {
		return dossier;
	}
	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
	public Intervention getIntervention() {
		return intervention;
	}
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	public Livraison getLivraison() {
		return livraison;
	}
	public void setLivraison(Livraison livraison) {
		this.livraison = livraison;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
