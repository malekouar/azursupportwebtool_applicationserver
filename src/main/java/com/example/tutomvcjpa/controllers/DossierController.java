package com.example.tutomvcjpa.controllers;


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

import com.example.tutomvcjpa.entities.Client;
import com.example.tutomvcjpa.entities.Dossier;
import com.example.tutomvcjpa.entities.Etat;
import com.example.tutomvcjpa.repositories.ClientRepository;
import com.example.tutomvcjpa.repositories.DossierRepository;
import com.example.tutomvcjpa.repositories.EtatRepository;

@Controller
public class DossierController {
    
    private final DossierRepository dossierRepository;
    private final ClientRepository clientRepository;
    private final EtatRepository etatRepository;

    @Autowired
    public DossierController(DossierRepository dossierRepository, ClientRepository clientRepository, EtatRepository etatRepository) {
        this.dossierRepository = dossierRepository;
        this.clientRepository = clientRepository;
        this.etatRepository = etatRepository;
    }

    @GetMapping("/dossiers")
    public String getAll(Model model) {
        model.addAttribute("dossiers", dossierRepository.findAll());
        return "dossiers";
    }

    @GetMapping("/dossier/new")
    public String showNewDossierForm(Dossier dossier, Model model) {
    	model.addAttribute("clients", clientRepository.findAll());
    	model.addAttribute("etats", etatRepository.findAll());
    	dossier.setId(-2);    	
        return "add-dossier";
    }
    
    @GetMapping("/dossier/new/{idclient}")
    public String showNewDossierFormByClientId(Dossier dossier, Model model, @PathVariable("idclient") long idclient) {
    	dossier.setClient(clientRepository
    						.findById(idclient).orElseThrow(
    									() -> new IllegalArgumentException("Invalid dossier Id:" + idclient)));
    	model.addAttribute("etats", etatRepository.findAll());
    	dossier.setId(-1);
    	return "add-dossier";
    }

    @PostMapping("/dossier/add")
    public String addDossier1(@Valid Dossier dossier, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("client", clientRepository.findById(dossier.getClient().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + (dossier.getClient().getId()))));
        	model.addAttribute("etats", etatRepository.findAll());        	
            return "add-dossier";
        }
        dossier.setDateDebut(new Date());
        dossierRepository.save(dossier);
        model.addAttribute("dossiers", dossierRepository.findAll());
        return "dossiers";
    }

    @GetMapping("/dossier/view/{id}")
    public String showViewDossier(@PathVariable("id") long id, Model model) {
        Dossier dossier = dossierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + id));
        model.addAttribute("dossier", dossier);
    	model.addAttribute("client", clientRepository.findById(dossier.getClient().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid client Id:" + dossier.getClient().getId())));        
    	model.addAttribute("etat", etatRepository.findById(dossier.getEtat().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid etat Id:" + dossier.getEtat().getId())));         
        return "view-dossier";
    }
    
    @GetMapping("/dossier/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Dossier dossier = dossierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + id));
    	model.addAttribute("dossier", dossier);
    	model.addAttribute("etats", etatRepository.findAll());    	
    	return "update-dossier";
    }
    
    @PostMapping("/dossier/update/{id}")
    public String updateDossier(@PathVariable("id") int id, @Valid Dossier dossier, BindingResult result, Model model) {
        if (result.hasErrors()) {
            dossier.setId(id);
            return "dossiers";
        }
        
        dossierRepository.save(dossier);
        model.addAttribute("dossiers", dossierRepository.findAll());
        return "dossiers";
    }
    
    @GetMapping("/dossier/delete/{id}")
    public String deleteDossier(@PathVariable("id") long id, Model model) {
        Dossier dossier = dossierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + id));
        dossierRepository.delete(dossier);
        model.addAttribute("dossiers", dossierRepository.findAll());
        return "dossiers";
    }
}
