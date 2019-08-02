package azur.support.webtool.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import azur.support.webtool.entities.Config;
import azur.support.webtool.repositories.ConfigRepository;

@Controller
public class ConfigController {
    
    private final ConfigRepository configRepository;

    @Autowired
    public ConfigController(ConfigRepository configRepository) {
        this.configRepository = configRepository;
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
