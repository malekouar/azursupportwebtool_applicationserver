package azur.support.webtool.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import azur.support.webtool.entities.Client;
import azur.support.webtool.entities.Config;
import azur.support.webtool.entities.Dossier;
import azur.support.webtool.entities.Serveur;
import azur.support.webtool.repositories.ClientRepository;

@Controller
public class ClientController {
    
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public String getAll(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }

    @GetMapping("/client/new")
    public String showNewClientForm(Client client) {
        return "add-client";
    }
    
    @GetMapping("/client/wizard/newclient")
    public String showNewClientWizardForm(Client client) {
    	return "wizard/add-client";
    }
    
    @GetMapping("/client/wizard/newconfig")
    public String showNewConfigWizardForm(Client client, Config config) {
    	return "wizard/add-config";
    }
    
    @GetMapping("/client/wizard/newserveur")
    public String showNewServeurWizardForm(Client client, Serveur serveur) {
    	return "wizard/add-serveur";
    }
    
    @GetMapping("/client/wizard/newdossier")
    public String showNewDossierWizardForm(Client client, Dossier dossier) {
    	return "wizard/add-dossier";
    }
    
    @GetMapping("/client/wizard")
    public String showWizardNewClientForm(Client client) {
    	return "stepper";
    }

    @PostMapping("/client/add")
    public String addClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-client";
        }

        clientRepository.save(client);
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }
    
    //data: {raisonSociale,contact,tel,mobile,email},
    @GetMapping("/client/add2")
    public String addClient2(@Valid 
    		String raisonSociale, 
    		String contact, 
    		String tel, 
    		String mobile, 
    		String email
    		//, 
    		//Model model
    		) {
//    	if (result.hasErrors()) {
//    		return "add-client";
//    	}
    	
    	Client client = new Client();
    	client.setRaisonSociale(raisonSociale);
    	client.setContact(contact);
    	client.setTel(tel);
    	client.setMobile(mobile);
    	client.setEmail(email);

    	clientRepository.save(client);
    	//model.addAttribute("add-OK", null);
    	return "wizard/add-OK";
    }

    @GetMapping("/client/view/{id}")
    public String showViewClient(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        model.addAttribute("client", client);
        return "view-client";
    }
    
    @GetMapping("/client/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
    	model.addAttribute("client", client);
    	return "update-client";
    }
    
    @PostMapping("/client/update/{id}")
    public String updateClient(@PathVariable("id") int id, @Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "clients";
        }
        
        clientRepository.save(client);
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }
    
    @GetMapping("/client/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + id));
        clientRepository.delete(client);
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }
}
