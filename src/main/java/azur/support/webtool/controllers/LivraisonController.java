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

import azur.support.webtool.entities.Intervention;
import azur.support.webtool.entities.Livraison;
import azur.support.webtool.entities.Etat;
import azur.support.webtool.repositories.InterventionRepository;
import azur.support.webtool.repositories.LivraisonRepository;
import azur.support.webtool.repositories.EtatRepository;

@Controller
public class LivraisonController {
    
    private final LivraisonRepository livraisonRepository;
    private final InterventionRepository interventionRepository;
    private final EtatRepository etatRepository;

    @Autowired
    public LivraisonController(LivraisonRepository livraisonRepository, InterventionRepository interventionRepository, EtatRepository etatRepository) {
        this.livraisonRepository = livraisonRepository;
        this.interventionRepository = interventionRepository;
        this.etatRepository = etatRepository;
    }

    @GetMapping("/livraisons")
    public String getAll(Model model) {
        model.addAttribute("livraisons", livraisonRepository.findAll());
        return "livraisons";
    }

    @GetMapping("/livraison/new")
    public String showNewLivraisonForm(Livraison livraison, Model model) {
    	model.addAttribute("interventions", interventionRepository.findAll());
    	model.addAttribute("etats", etatRepository.findAll());
    	livraison.setId(-2);    	
        return "add-livraison";
    }
    
    @GetMapping("/livraison/new/{idintervention}")
    public String showNewLivraisonFormByInterventionId(Livraison livraison, Model model, @PathVariable("idintervention") long idintervention) {
    	livraison.setIntervention(interventionRepository
    						.findById(idintervention).orElseThrow(
    									() -> new IllegalArgumentException("Invalid intervention Id:" + idintervention)));
    	model.addAttribute("etats", etatRepository.findAll());
    	livraison.setId(-1);
    	return "add-livraison";
    }

    @PostMapping("/livraison/add")
    public String addLivraison1(@Valid Livraison livraison, BindingResult result, Model model) {
        if (result.hasErrors()) {
       	model.addAttribute("intervention", interventionRepository.findById(livraison.getIntervention().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid livraison Id:" + (livraison.getIntervention().getId()))));
        	model.addAttribute("etats", etatRepository.findAll());        	
            return "add-livraison";
        }
        livraison.setDateLivraison(new Date());
        livraisonRepository.save(livraison);
        model.addAttribute("livraisons", livraisonRepository.findAll());
        return "livraisons";
    }

    @GetMapping("/livraison/view/{id}")
    public String showViewLivraison(@PathVariable("id") long id, Model model) {
        Livraison livraison = livraisonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid livraison Id:" + id));
        model.addAttribute("livraison", livraison);
    	model.addAttribute("intervention", interventionRepository.findById(livraison.getIntervention().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid intervention Id:" + livraison.getIntervention().getId())));        
    	model.addAttribute("etat", etatRepository.findById(livraison.getEtat().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid etat Id:" + livraison.getEtat().getId())));         
        return "view-livraison";
    }
    
    @GetMapping("/livraison/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
    	Livraison livraison = livraisonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid livraison Id:" + id));
    	model.addAttribute("livraison", livraison);
    	model.addAttribute("etats", etatRepository.findAll());    	
    	return "update-livraison";
    }
    
    @PostMapping("/livraison/update/{id}")
    public String updateLivraison(@PathVariable("id") int id, @Valid Livraison livraison, BindingResult result, Model model) {
        if (result.hasErrors()) {
            livraison.setId(id);
            return "livraisons";
        }
        
        livraisonRepository.save(livraison);
        model.addAttribute("livraisons", livraisonRepository.findAll());
        return "livraisons";
    }
    
    @GetMapping("/livraison/delete/{id}")
    public String deleteLivraison(@PathVariable("id") long id, Model model) {
        Livraison livraison = livraisonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid livraison Id:" + id));
        livraisonRepository.delete(livraison);
        model.addAttribute("livraisons", livraisonRepository.findAll());
        return "livraisons";
    }
    
//    public static void main(String[] args) {
//		String codeJournal = null;
//		String codeExercie = null;
//		String numeroPiece = null;
//		
//    	String code = "26-2019-00006145";
//		System.out.println("code 1 = " + code );
//    	
//		if (code.indexOf("-") > -1) {
//			codeJournal = code.substring(0,code.indexOf("-"));
//			code = code.substring(code.indexOf("-")+1);
//			System.out.println("code 2 = " + code );
//		}
//		if (code.indexOf("-") > -1) {
//			codeExercie = code.substring(0,code.indexOf("-"));
//			code = code.substring(code.indexOf("-")+1);
//			System.out.println("code 3 = " + code );
//		}
//		
//		numeroPiece = code;
//		
//		System.out.println("code = " + code );
//		System.out.println("codeJournal = " + codeJournal );
//		System.out.println("codeExercie = " + codeExercie );
//		System.out.println("numeroPiece = " + numeroPiece );
//		
//	}
}
