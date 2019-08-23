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
import azur.support.webtool.repositories.ClientRepository;
import azur.support.webtool.repositories.ConfigRepository;

@Controller
public class ConfigController {
    
    private final ClientRepository clientRepository;
    private final ConfigRepository configRepository;

    @Autowired
    public ConfigController(ConfigRepository configRepository, ClientRepository clientRepository) {
        this.configRepository = configRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/configs")
    public String getAll(Model model) {
        model.addAttribute("configs", configRepository.findAll());
        return "configs";
    }

    @GetMapping("/config/new")
    public String showNewConfigForm(Config config) {
        return "add-config";
    }

    @PostMapping("/config/add")
    public String addConfig(@Valid Config config, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-config";
        }

        configRepository.save(config);
        model.addAttribute("configs", configRepository.findAll());
        return "configs";
    }
    
    @GetMapping("/config/add2")
    public String addConfig2(@Valid long clientId
    		, String teamviewerId
    		, String teamviewerPassword
    		, String vpnType
    		, String vpnIp
    		, String vpnLogin
    		, String vpnPassword
    		, Model model) {
    	
		String errorMessage = "";
		String comment="";
		boolean isFormEmpty = teamviewerId.isEmpty() && vpnType.isEmpty();
		boolean isTVEmty = (!teamviewerId.isEmpty() || !teamviewerPassword.isEmpty()) && !(!teamviewerId.isEmpty() && !teamviewerPassword.isEmpty());
		boolean isVpnEmpty = !vpnType.isEmpty() && !(!vpnIp.isEmpty() && !vpnLogin.isEmpty() && !vpnPassword.isEmpty());
		
		model.addAttribute("step", "config");
		model.addAttribute("stepindex", "1");
		
		try {

//			if ((teamviewerId == null || teamviewerId.isEmpty()) && (vpnType == null || vpnType.isEmpty())) {
			if (isFormEmpty) {
				comment= "<li>Vous devez configurer un mode d'accès. </li>";
				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;
			} 
			
			else if (isTVEmty) {
				comment= "<li>Configuration Teamviewer incomplète. </li>";
				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;
			} 
			
			else if (isVpnEmpty) {
				comment= "<li>Configuration VPN incomplète. </li>";
				errorMessage = errorMessage==null || errorMessage.isEmpty()  ? comment  : errorMessage + comment;				
			} 
			
			if(errorMessage != null && ! errorMessage.isEmpty()) {
				errorMessage = "<ul style = 'color:red'>" + errorMessage +"</ul>";
				throw new Exception(errorMessage);
			
			} else {
				Config config = new Config();
				Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientId));
				config.setClient(client);
				config.setTeamviewerId(teamviewerId);
				config.setTeamviewerPassword(teamviewerPassword);
				config.setVpnType(vpnType);
				config.setVpnIp(vpnIp);
				config.setVpnLogin(vpnLogin);
				config.setVpnPassword(vpnPassword);
				
				configRepository.save(config);
				model.addAttribute("strConfigId", String.valueOf(config.getId()));
				model.addAttribute("retourconfig", "OK");
				return "wizard/OK";
			}		
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("retourconfig", "KO");
			return "wizard/KO";
		}
    	

		
//    	configRepository.save(config);
//    	model.addAttribute("configs", configRepository.findAll());
//    	return "configs";
    }

    @GetMapping("/config/view/{id}")
    public String showViewConfig(@PathVariable("id") long id, Model model) {
        Config config = configRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid config Id:" + id));
        model.addAttribute("config", config);
        return "view-config";
    }
    
    @GetMapping("/config/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Config config = configRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid config Id:" + id));
    	model.addAttribute("config", config);
    	return "update-config";
    }
    

    @GetMapping("/client/wizard/editconfig/{clientid}")
    public String showNewConfigWizardForm(@PathVariable("clientid") long clientid, Config config, Model model) {
        Client client = clientRepository.findById(clientid).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + clientid));
    	model.addAttribute("client", client);
        config = configRepository.findByClient(client).get(0);
        model.addAttribute("config", config);
    
    	return "wizard/edit-config";
    }      
    
    @PostMapping("/config/update/{id}")
    public String updateConfig(@PathVariable("id") int id, @Valid Config config, BindingResult result, Model model) {
        if (result.hasErrors()) {
            config.setId(id);
            return "configs";
        }
        
        configRepository.save(config);
        model.addAttribute("configs", configRepository.findAll());
        return "configs";
    }
    
    @GetMapping("/config/delete/{id}")
    public String deleteConfig(@PathVariable("id") long id, Model model) {
        Config config = configRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid config Id:" + id));
        configRepository.delete(config);
        model.addAttribute("configs", configRepository.findAll());
        return "configs";
    }
  
}
