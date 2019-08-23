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

import azur.support.webtool.entities.Client;
import azur.support.webtool.entities.Config;
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
    
    
    /*
				var clientId = '[(${client.id})]';
				var serveurType = $("#serveurType").val();
				var serveurNom = $("#serveurNom").val();
				var serveurIp = $("#serveurIp").val();
				var Login = $("#Login").val();
				var password = $("#password").val();     
     
     
     
     */
    
    @GetMapping("/serveur/add2")
    public String addServeur2(@Valid long clientId
    		, String serveurType
    		, String serveurNom
    		, String serveurIp
    		, String login
    		, String password
    		, Model model) {
    	
		String errorMessage = "";
		String comment="";
		boolean isFormError = serveurType.isEmpty() || serveurNom.isEmpty() || serveurIp.isEmpty() || login.isEmpty() || password.isEmpty();
		//boolean isTVEmty = (!teamviewerId.isEmpty() || !teamviewerPassword.isEmpty()) && !(!teamviewerId.isEmpty() && !teamviewerPassword.isEmpty());
		//boolean isVpnEmpty = !vpnType.isEmpty() && !(!vpnIp.isEmpty() && !vpnLogin.isEmpty() && !vpnPassword.isEmpty());
		
		model.addAttribute("step", "serveur");
		model.addAttribute("stepindex", "1");
		
		try {

//			if ((teamviewerId == null || teamviewerId.isEmpty()) && (vpnType == null || vpnType.isEmpty())) {
			if (isFormError) {
				comment= "<li>Fomulaire incomplet : </li>";
				if(serveurType.isEmpty()) 	comment += "<li>- Type du serveur requis. </li>";
				if(serveurNom.isEmpty()) 	comment += "<li>- Nom du serveur requis. </li>";
				if(serveurIp.isEmpty()) 	comment += "<li>- IP du serveur requis. </li>";
				if(login.isEmpty()) 		comment += "<li>- Login du serveur requis. </li>";
				if(password.isEmpty()) 		comment += "<li>- Mot du passe de serveur requis. </li>";
				
				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;
			} 
			
//			else if (isTVEmty) {
//				comment= "<li>Serveururation Teamviewer incomplète. </li>";
//				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;
//			} 
//			
//			else if (isVpnEmpty) {
//				comment= "<li>Serveururation VPN incomplète. </li>";
//				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;				
//			} 
//			
			if(errorMessage != null && ! errorMessage.isEmpty()) {
				errorMessage = "<ul style = 'color:red'>" + errorMessage +"</ul>";
				throw new Exception(errorMessage);
			
			} else {
				Serveur serveur = new Serveur();
				Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
				serveur.setClient(client);
				serveur.setServeurType(serveurType);
				serveur.setServeurNom(serveurNom);
				serveur.setServeurIp(serveurIp);
				serveur.setLogin(login);
				serveur.setPassword(password);
				
				serveurRepository.save(serveur);
				model.addAttribute("strServeurId", String.valueOf(serveur.getId()));
				model.addAttribute("retourserveur", "OK");
				return "wizard/OK";
			}		
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("retourserveur", "KO");
			return "wizard/KO";
		}
    }    

    @GetMapping("/client/wizard/editserveur/{clientid}")
    public String showEditServeurWizardForm(@PathVariable("clientid") long clientid, Serveur serveur, Model model) {
        Client client = clientRepository.findById(clientid).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientid));
    	model.addAttribute("client", client);
        serveur = serveurRepository.findByClient(client).get(0);
        model.addAttribute("serveur", serveur);
    
    	return "wizard/edit-serveur";
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
