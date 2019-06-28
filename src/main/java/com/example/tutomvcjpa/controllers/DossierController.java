package com.example.tutomvcjpa.controllers;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tutomvcjpa.entities.Dossier;
import com.example.tutomvcjpa.repositories.DossierRepository;

@Controller
public class DossierController {
    
    private final DossierRepository dossierRepository;

    @Autowired
    public DossierController(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    @GetMapping("/dossiers")
    public String getAll(Model model) {
        model.addAttribute("dossiers", dossierRepository.findAll());
        return "dossiers";
    }

    @GetMapping("/dossier/new")
    public String showNewDossierForm(Dossier dossier) {
        return "add-dossier";
    }

    @PostMapping("/dossier/add")
    public String addDossier(@Valid Dossier dossier, BindingResult result, Model model) {
        if (result.hasErrors()) {
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
        return "view-dossier";
    }
    
    @GetMapping("/dossier/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Dossier dossier = dossierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid dossier Id:" + id));
    	model.addAttribute("dossier", dossier);
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
