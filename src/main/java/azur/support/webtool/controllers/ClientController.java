package azur.support.webtool.controllers;


import javax.servlet.http.HttpSession;
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
    
    // ----------- WIZARD ------------------------------
    
    @GetMapping("/client/wizard/newclient")
    public String showNewClientWizardForm(Client client) {
    	return "wizard/add-client";
    }

    @GetMapping("/client/wizard/newconfig/{clientid}")
    public String showNewConfigWizardForm(@PathVariable("clientid") long clientid, Config config, HttpSession session) {
    	
    	Client theClient = (Client)session.getAttribute("client");
    	
    	if(theClient == null) {
            theClient = clientRepository.findById(clientid).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientid));
        	session.setAttribute("client", theClient);
    	} 
    	
    	//model.addAttribute("client", theClient);
    	return "wizard/add-config";
    }      

    @GetMapping("/client/wizard/newserveur")
    public String showNewServeurWizardForm(Long clientId, Serveur serveur) {
    	return "wizard/add-serveur";
    }

    @GetMapping("/client/wizard/newdossier")
    public String showNewDossierWizardForm(Long clientId, Dossier dossier) {
    	return "wizard/add-dossier";
    }
    
    @GetMapping("/client/wizard")
    public String showWizardNewClientForm() {
    	return "stepper";
    }

    // ----------- Fin WIZARD ------------------------------
    
    @PostMapping("/client/add")
    public String addClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-client";
        }

        clientRepository.save(client);
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }
    
	// data: {raisonSociale,contact,tel,mobile,email},
	@GetMapping("/client/add2")
	public String addClient2(@Valid String raisonSociale, String contact, String tel, String mobile, String email,
			Model model) {
		
		String errorMessage = "";
		String comment="";
		
		model.addAttribute("step", "client");
		model.addAttribute("stepindex", "0");		
		
		try {

			if (raisonSociale == null || raisonSociale.isEmpty()) {
				comment= "<li>La raison sociale est obligatoire. </li>";
				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;
			} 
			
			if (contact == null || contact.isEmpty()) {
				comment= "<li>Le contact est obligatoire. </li>";
				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;
			} 
			
			if (email == null || email.isEmpty()|| !email.contains("@")) {
				comment= "<li>Vous devez saisir une adresse e-mail valide. </li>";
				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;				
			} 
			
			
			if(errorMessage !=null && ! errorMessage.isEmpty()) {
				errorMessage = "<ul style = 'color:red'>" + errorMessage +"</ul>";
				throw new Exception(errorMessage);
			
			} else {
				Client client = new Client();
				client.setRaisonSociale(raisonSociale);
				client.setContact(contact);
				client.setTel(tel);
				client.setMobile(mobile);
				client.setEmail(email);
				
				clientRepository.save(client);
				model.addAttribute("strClientId", String.valueOf(client.getId()));
				model.addAttribute("retourclient", "OK");
				return "wizard/OK";
			}

		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("retourclient", "KO");
			return "wizard/KO";
		}

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
