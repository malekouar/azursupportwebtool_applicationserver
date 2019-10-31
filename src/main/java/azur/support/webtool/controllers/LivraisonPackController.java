package azur.support.webtool.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import azur.support.webtool.entities.Client;
import azur.support.webtool.entities.Dossier;
import azur.support.webtool.entities.Etat;
import azur.support.webtool.entities.Intervention;
import azur.support.webtool.entities.Livraison;
import azur.support.webtool.forms.LivraisonPackForm;
import azur.support.webtool.repositories.ClientRepository;
import azur.support.webtool.repositories.DossierRepository;
import azur.support.webtool.repositories.EtatRepository;
import azur.support.webtool.repositories.InterventionRepository;
import azur.support.webtool.repositories.LivraisonRepository;

@Controller
public class LivraisonPackController {
	
    private final ClientRepository clientRepository;
    private final DossierRepository dossierRepository;
    private final InterventionRepository interventionRepository;
    private final LivraisonRepository livraisonRepository;
    private final EtatRepository etatRepository;
	
	
    public LivraisonPackController(ClientRepository clientRepository, DossierRepository dossierRepository,
			InterventionRepository interventionRepository, LivraisonRepository livraisonRepository,
			EtatRepository etatRepository) {
		this.clientRepository = clientRepository;
		this.dossierRepository = dossierRepository;
		this.interventionRepository = interventionRepository;
		this.livraisonRepository = livraisonRepository;
		this.etatRepository = etatRepository;
	}


	@GetMapping("/livraisonPack/new")
    public String showNewLivraisonPackForm(LivraisonPackForm livraisonPackForm, Model model) {
    	//model.addAttribute("clients", clientRepository.findAll());
    	model.addAttribute("clients", clientRepository.findAll());
    	model.addAttribute("etats", etatRepository.findAll());
        return "add-livraisonpack";
    }
	
    @PostMapping("/livraisonPack/add")
    public String addLivraisonPack(@Valid LivraisonPackForm livraisonPackForm, BindingResult result, Model model) {
        
    	if (result.hasErrors()) {
        	model.addAttribute("client", clientRepository.findById(livraisonPackForm.getClient().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + (livraisonPackForm.getClient().getId()))));
        	model.addAttribute("etats", etatRepository.findAll());        	
            return "add-livraisonpack";
        }
        
    	try {
    		// Dossier
	        livraisonPackForm.getDossier().setClient(livraisonPackForm.getClient());
	        livraisonPackForm.getDossier().setDateDebut(new Date());
	        Dossier dossier = dossierRepository.save(livraisonPackForm.getDossier());
	        
	        // Intervention
	        livraisonPackForm.getIntervention().setDossier(dossier);
	        livraisonPackForm.getIntervention().setDateDebut(dossier.getDateDebut());
	        livraisonPackForm.getIntervention().setResponsable(dossier.getResponsable());
	        livraisonPackForm.getIntervention().setEtat(dossier.getEtat());
	        
	        Intervention intervention = interventionRepository.save(livraisonPackForm.getIntervention());
	        
	        // Livraison
	        livraisonPackForm.getLivraison().setIntervention(intervention);
	        
	        if(livraisonPackForm.getLivraison().getDateLivraison() == null ) {
	        	livraisonPackForm.getLivraison().setDateLivraison(intervention.getDateDebut());
	        }
	        
	        livraisonRepository.save(livraisonPackForm.getLivraison());
	        model.addAttribute("livraisons", livraisonRepository.findAll());
	        return "livraisons";

        } catch (Exception e) {
        	model.addAttribute("clients", clientRepository.findAll());
        	model.addAttribute("etats", etatRepository.findAll());
        	return "add-livraisonpack";
		}
    }
  
/*
	private Client client;
	private Dossier dossier;
	private Intervention intervention;
	private Livraison livraison;
	private Etat etat;
 */	
    @GetMapping("/livraisonPack/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	LivraisonPackForm livraisonPack = new LivraisonPackForm();
    	
    	Livraison livraison 		= livraisonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid livraison Id:" + id));
    	Intervention intervention 	= interventionRepository.findById(livraison.getIntervention().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid intervention Id:" + id));
    	Dossier dossier 			= dossierRepository.findById(intervention.getDossier().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + id));
    	Client client 				= clientRepository.findById(dossier.getClient().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
    	
    	livraisonPack.setClient(client);
    	livraisonPack.setDossier(dossier);
    	livraisonPack.setIntervention(intervention);
    	livraisonPack.setLivraison(livraison);
    	
    	model.addAttribute("livraisonPack", livraisonPack);
    	model.addAttribute("etats", etatRepository.findAll());    	
    	return "update-livraisonpack";
    }	
    
   
    
    @PostMapping("/livraisonPack/update")
    public String updateLivraisonPack(@Valid LivraisonPackForm livraisonPackForm, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
    		model.addAttribute("client", clientRepository.findById(livraisonPackForm.getClient().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + (livraisonPackForm.getClient().getId()))));
    		model.addAttribute("etats", etatRepository.findAll());        	
    		return "add-dossierPack";
    	}
    	
    	try {
//    		// Dossier
//    		livraisonPackForm.getDossier().setClient(livraisonPackForm.getClient());
//    		livraisonPackForm.getDossier().setDateDebut(new Date());
//    		Dossier dossier = dossierRepository.save(livraisonPackForm.getDossier());
//    		
//    		
//    		// Intervention
//    		livraisonPackForm.getIntervention().setDossier(dossier);
//    		livraisonPackForm.getIntervention().setDateDebut(dossier.getDateDebut());
//    		livraisonPackForm.getIntervention().setResponsable(dossier.getResponsable());
//    		livraisonPackForm.getIntervention().setEtat(dossier.getEtat());
//    		
//    		Intervention intervention = interventionRepository.save(livraisonPackForm.getIntervention());
    		
    		// Livraison
    		livraisonPackForm.getLivraison().setIntervention(livraisonPackForm.getIntervention());
    		
//    		if(livraisonPackForm.getLivraison().getDateLivraison() == null ) {
//    			livraisonPackForm.getLivraison().setDateLivraison(intervention.getDateDebut());
//    		}
    		
    		livraisonRepository.save(livraisonPackForm.getLivraison());
    		
    		model.addAttribute("livraisons", livraisonRepository.findAll());
    		
    		return "livraisons";
    		
    	} catch (Exception e) {
    		//model.addAttribute("clients", clientRepository.findAll());
    		model.addAttribute("etats", etatRepository.findAll());
    		return "update-livraisonpack";
    	}
    }
    
//    
//    @GetMapping("/livraisonPack/search/{search}")
//    public String searchForm(@PathVariable("search") String search, Model model) {
//    	LivraisonPackForm livraisonPack = new LivraisonPackForm();
//    	
////    	Livraison livraison 		= livraisonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid livraison Id:" + id));
////    	Intervention intervention 	= interventionRepository.findById(livraison.getIntervention().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid intervention Id:" + id));
////    	Dossier dossier 			= dossierRepository.findById(intervention.getDossier().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + id));
//    	
//    	List<Client> clients 				= clientRepository.findaAllByRaisonSocialeOrByContact(search).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
//    	
//    	livraisonPack.setClient(clients);
////    	livraisonPack.setDossier(dossier);
////    	livraisonPack.setIntervention(intervention);
////    	livraisonPack.setLivraison(livraison);
//    	
//    	model.addAttribute("livraisonPack", livraisonPack);
//    	model.addAttribute("etats", etatRepository.findAll());    	
//    	return "update-livraisonpack";
//    }	
    
     
     
}
