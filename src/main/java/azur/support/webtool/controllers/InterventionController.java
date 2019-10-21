package azur.support.webtool.controllers;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import azur.support.webtool.entities.Dossier;
import azur.support.webtool.entities.Intervention;
import azur.support.webtool.entities.Etat;
import azur.support.webtool.repositories.DossierRepository;
import azur.support.webtool.repositories.InterventionRepository;
import azur.support.webtool.repositories.EtatRepository;

@Controller
public class InterventionController {
    
    private final InterventionRepository interventionRepository;
    private final DossierRepository dossierRepository;
    private final EtatRepository etatRepository;

    @Autowired
    public InterventionController(InterventionRepository interventionRepository, DossierRepository dossierRepository, EtatRepository etatRepository) {
        this.interventionRepository = interventionRepository;
        this.dossierRepository = dossierRepository;
        this.etatRepository = etatRepository;
    }

    @GetMapping("/interventions")
    public String getAll(Model model) {
        model.addAttribute("interventions", interventionRepository.findAll());
        return "interventions";
    }

    @GetMapping("/intervention/new")
    public String showNewInterventionForm(Intervention intervention, Model model) {
    	model.addAttribute("dossiers", dossierRepository.findAll());
    	model.addAttribute("etats", etatRepository.findAll());
    	intervention.setId(-2);    	
        return "add-intervention";
    }
    
    @GetMapping("/intervention/new/{iddossier}")
    public String showNewInterventionFormByDossierId(Intervention intervention, Model model, @PathVariable("iddossier") long iddossier) {
    	intervention.setDossier(dossierRepository
    						.findById(iddossier).orElseThrow(
    									() -> new IllegalArgumentException("Invalid intervention Id:" + iddossier)));
    	model.addAttribute("etats", etatRepository.findAll());
    	intervention.setId(-1);
    	return "add-intervention";
    }

    @PostMapping("/intervention/add")
    public String addIntervention1(@Valid Intervention intervention, BindingResult result, Model model) {
        if (result.hasErrors()) {
       	model.addAttribute("dossier", dossierRepository.findById(intervention.getDossier().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid intervention Id:" + (intervention.getDossier().getId()))));
        	model.addAttribute("etats", etatRepository.findAll());        	
            return "add-intervention";
        }
        intervention.setDateDebut(new Date());
        interventionRepository.save(intervention);
        model.addAttribute("interventions", interventionRepository.findAll());
        return "interventions";
    }

    @GetMapping("/intervention/view/{id}")
    public String showViewIntervention(@PathVariable("id") long id, Model model) {
        Intervention intervention = interventionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid intervention Id:" + id));
        model.addAttribute("intervention", intervention);
    	model.addAttribute("dossier", dossierRepository.findById(intervention.getDossier().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + intervention.getDossier().getId())));        
    	model.addAttribute("etat", etatRepository.findById(intervention.getEtat().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid etat Id:" + intervention.getEtat().getId())));         
        return "view-intervention";
    }
    
    @GetMapping("/intervention/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Intervention intervention = interventionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid intervention Id:" + id));
    	model.addAttribute("intervention", intervention);
    	model.addAttribute("etats", etatRepository.findAll());    	
    	return "update-intervention";
    }
    
    @PostMapping("/intervention/update/{id}")
    public String updateIntervention(@PathVariable("id") int id, @Valid Intervention intervention, BindingResult result, Model model) {
        if (result.hasErrors()) {
            intervention.setId(id);
            return "interventions";
        }
        
        interventionRepository.save(intervention);
        model.addAttribute("interventions", interventionRepository.findAll());
        return "interventions";
    }
    
    @GetMapping("/intervention/delete/{id}")
    public String deleteIntervention(@PathVariable("id") long id, Model model) {
        Intervention intervention = interventionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid intervention Id:" + id));
        interventionRepository.delete(intervention);
        model.addAttribute("interventions", interventionRepository.findAll());
        return "interventions";
    }
}
