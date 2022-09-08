package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.Employe;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.service.EmployeService;



@Controller
public class EmployeController {
	//Best pracice > passer par un service !
	/*
	@Autowired
	private EmployeService employeService;
	*/
	
	@Autowired
	private WorkerRepository workerRepo;
	
	@GetMapping("/homeEmploye")
	public String AllHome(Employe worker, Model model) {
		List<Employe> workers = workerRepo.findAll();
		
		System.out.println(workers);
		
		model.addAttribute("workers",workers);
		
		return "homeEmploye";
	}
	
	public String Home(Employe worker) {
		return "homeEmploye";
	}
	
	@PostMapping("/homeEmploye")
	public String PostHomeEmploye(@Validated Employe worker, BindingResult bindingResult, Model model) {
		findworkers(model);
		
		if(bindingResult.hasErrors()) {
			//System.out.println(bindingResult.getErrorCount());
			return "homeEmploye";
		}
		 if(workerRepo.findByEmail(worker.getEmail())!=null) {
			 bindingResult.addError(new FieldError("worker","email","Le mail existe déjà"));
		 return "homeEmploye";
		 } else {
			 
			 System.out.println("test sur WORKER >>>>>>\n"+worker.toString()+"\n");
			 workerRepo.save(worker);
		 }
		
		
		return "redirect:/homeEmploye";
	}
	
	@GetMapping("/workerF/{id}")
	public String Update(@PathVariable(value="id")Long workerId, Model model) {
		Employe work = null;
		Optional<Employe> travailleur = workerRepo.findById(workerId);
		// Optional permet de gérer les cas de bug, id non trouvé ou null ici... sinon l'appli crash!
		if(travailleur.isPresent()) {
			work=travailleur.get();
			// vient de Optional permet de 'get()' toutes les infos du user dont l'ID a été trouvé dans la BDD
			
			model.addAttribute("worky", work);
			System.out.println("TEST sur WORK !!!!! >>>> "+"\n"+model.getAttribute("worky").toString()+"\n");
			
		}
		return "workerF";
	}
	
	@PostMapping("/workerF/{id}") 
	public String Update(@PathVariable(value="id")Long workerId, @Validated Employe worky, Model model, BindingResult bindingResult) {
		// @Validated va vérifier la concordance des paramètres entrés par rapport  à l'objet ciblé ici Employe
		//@PathVariable prend la value dans l'url appelé {id} et l'affecte à sa cible workerId de type 'Long'
		
		Optional<Employe> travailleur = workerRepo.findById(workerId);
		//Optonal va donner des posibilités de gestion de la variable récupérée (null, vide, etc...)
		
		Employe salarie = null;
		
		if(bindingResult.hasErrors()) {
			//System.out.println(bindingResult.getErrorCount());
			return "homeEmploye";
		}
		
		if(travailleur.isPresent()) {
			// *.isPresent() est autorisé par 'Optional'
			
			salarie = travailleur.get();
			// vient de Optional permet de 'getter()' toutes les infos du user dont l'ID a été trouvé dans la BDD
		
		salarie.setNom(worky.getNom()); // on donne à 'nom' de salarie le nom de worky objet utilisé par les inputs
		salarie.setPrenom(worky.getPrenom());// on donne à 'prenom' de salarie le prenom de worky objet utilisé par les inputs
		salarie.setEmail(worky.getEmail());// on donne à 'email'de salarie l' 'email' de worky objet utilisé par les inputs
		salarie.setFonction(worky.getFonction());// on donne à 'fonction' de salarie la 'fonction' de worky objet utilisé par les inputs
		
		
		workerRepo.save(salarie); // On save les modification de salarie qui est l'employé selectionné par l'id
		}
		
		return "redirect:/homeEmploye";
	}
	
	@GetMapping("/delete/{id}")
	public String Delete(@PathVariable(value="id")Long workerId, Model model) {
		Employe work = null;
		Optional<Employe> travailleur = workerRepo.findById(workerId);
		// Optional permet de gérer les cas de bug, id non trouvé ou null ici... sinon l'appli crash!
		if(travailleur.isPresent()) {
			work=travailleur.get();
			// vient de Optional permet de 'get()' toutes les infos du user dont l'ID a été trouvé dans la BDD
			
			model.addAttribute("worky", work);
			System.out.println("TEST sur WORK !!!!! >>>> "+"\n"+model.getAttribute("worky").toString()+"\n");
			
		}
		return "redirect:/homeEmploye";
	}
	
	public void findworkers(Model model){
		List<Employe> workers = workerRepo.findAll();
		
		model.addAttribute("workers", workers);
		
		//return users;
	}
}
