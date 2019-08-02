package azur.support.webtool.controllers;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import azur.support.webtool.entities.Serveur;
import azur.support.webtool.repositories.ClientRepository;
import azur.support.webtool.repositories.ConfigRepository;
import azur.support.webtool.repositories.ServeurRepository;

@Controller
public class ServeurController {
    
    private final ServeurRepository serveurRepository;
    private final ClientRepository clientRepository;
    //private final ConfigRepository configRepository;

    @Autowired
    public ServeurController(ServeurRepository serveurRepository, ClientRepository clientRepository, ConfigRepository configRepository) {
        this.serveurRepository = serveurRepository;
        this.clientRepository = clientRepository;
        //this.configRepository = configRepository;
    }

    @GetMapping("/serveurs")
    public String getAll(Model model) {
        model.addAttribute("serveurs", serveurRepository.findAll());
        return "serveurs";
    }

    @GetMapping("/serveur/new")
    public String showNewServeurForm(Serveur serveur, Model model) {
    	model.addAttribute("clients", clientRepository.findAll());
    	//model.addAttribute("configs", configRepository.findAll());
    	serveur.setId(-2);  
        return "add-serveur";
    }
    
    @GetMapping("/serveur/new/{idclient}")
    public String showNewServeurFormByClient(Serveur serveur, Model model, @PathVariable("idclient") long idclient) {
    	serveur.setClient(clientRepository
    							.findById(idclient).orElseThrow(
    									() -> new IllegalArgumentException("Invalid serveur Id:" + idclient)));
    	serveur.setId(-1);  
    	return "add-serveur";
    }

    @PostMapping("/serveur/add")
    public String addServeur(@Valid Serveur serveur, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	if(serveur.getId()==-1) {
            	model.addAttribute("client", clientRepository.findById(serveur.getClient().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + serveur.getClient().getId())));
        	} else {
            	model.addAttribute("clients", clientRepository.findAll());
        	}
            return "add-serveur";
        }
        serveurRepository.save(serveur);
        model.addAttribute("serveurs", serveurRepository.findAll());
        return "serveurs";
    }
    
//    @PostMapping("/serveur/add/{idconfig}")
//    public String addServeurByConfig(@Valid Serveur serveur, BindingResult result, Model model, @PathVariable("idconfig") long idconfig) {
//    	if (result.hasErrors()) {
//    		model.addAttribute("config", clientRepository.findById(idconfig).orElseThrow(() -> new IllegalArgumentException("Invalid config Id:" + idconfig)));
//    		//model.addAttribute("configs", configRepository.findAll());        	
//    		return "add-serveur";
//    	}
//    	serveurRepository.save(serveur);
//    	model.addAttribute("serveurs", serveurRepository.findAll());
//    	return "serveurs";
//    }

    @GetMapping("/serveur/view/{id}")
    public String showViewServeur(@PathVariable("id") long id, Model model) {
        Serveur serveur = serveurRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid serveur Id:" + id));
        model.addAttribute("serveur", serveur);
    	model.addAttribute("client", clientRepository.findById(serveur.getClient().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + serveur.getClient().getId())));        
    	//model.addAttribute("config", configRepository.findById(serveur.getConfig().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid config Id:" + serveur.getConfig().getId())));         
        return "view-serveur";
    }
    
    @GetMapping("/serveur/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Serveur serveur = serveurRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid serveur Id:" + id));
    	model.addAttribute("serveur", serveur);
    	model.addAttribute("clients", clientRepository.findAll());    	
    	return "update-serveur";
    }
    
    @PostMapping("/serveur/update/{id}")
    public String updateServeur(@PathVariable("id") int id, @Valid Serveur serveur, BindingResult result, Model model) {
        if (result.hasErrors()) {
            serveur.setId(id);
            return "serveurs";
        }
        
        serveurRepository.save(serveur);
        model.addAttribute("serveurs", serveurRepository.findAll());
        return "serveurs";
    }
    
    @GetMapping("/serveur/delete/{id}")
    public String deleteServeur(@PathVariable("id") long id, Model model) {
        Serveur serveur = serveurRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid serveur Id:" + id));
        serveurRepository.delete(serveur);
        model.addAttribute("serveurs", serveurRepository.findAll());
        return "serveurs";
    }
}
