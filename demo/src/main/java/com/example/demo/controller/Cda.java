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
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;



@Controller
public class Cda {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/home")
	public String Acceuil(User user, Model model){
		List<User> users = userRepo.findAll();
		
		model.addAttribute("users",users);
		// = request.setAttribute("users",users) en J2E
		return "home";
	}	
	
	public String Home(User user) {
		return "home";
	}
	
	@PostMapping("/home")
//	public String PostHome(@Validated User user, BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			return "home";
//		}
//		
//		
//		if(userRepo.findByEmail(user.getEmail()) == null) {
//		userRepo.save(user); // Ici on fait un create
//		}else {
//			bindingResult.addError(new FieldError ("user","email","Le mail existe déjà"));
//			System.out.println(bindingResult.getFieldErrorCount());
//		}
//		return "redirect:/home";
//	}
	
	public String postHome(@Validated User user, BindingResult bindingResult, Model model) {
		//model.addAttribute("users", findusers());
		findusers(model);
		
		System.out.println(bindingResult.hasErrors());
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getErrorCount());
			return "home";
		}
		
		if(userRepo.findByEmail(user.getEmail()) != null ) {
			
			bindingResult.addError(new FieldError( "user","email","Le mail existe deja"));
			System.out.println(bindingResult.getFieldError());
			return "home";
		}else {
			System.out.println("Dans le save");
			userRepo.save(user);
			return "redirect:/home";
		}
	}
	
	
	@GetMapping("/show/{id}")
		public String show(@PathVariable(value="id")Long userId, Model model) {
		User utilisateur = null;
			Optional<User> user=userRepo.findById(userId);
			// Optional permet de gérer les cas de bug, id non trouvé ou null ici... sinon l'appli crash!
			if(user.isPresent()) {
				utilisateur=user.get(); 
				// vient de Optional permet de 'get()' toutes les infos du user dont l'ID a été trouvé dans la BDD
				model.addAttribute("utilisateur",utilisateur);
				
			}
			return "show";
	}
	
	@PostMapping("/show/{id}")
    public String update(@PathVariable(value="id") Long userId,@Validated User utilisateur, Model model) {
        
        
        
        //version 1
        Optional<User> user=userRepo.findById(userId);
        User utilisateur2=null;
        if(user.isPresent()) {
            
            
            utilisateur2=user.get();
            
            utilisateur2.setNom(utilisateur.getNom());
            utilisateur2.setPrenom(utilisateur.getPrenom());
            utilisateur2.setEmail(utilisateur.getEmail());
            
            userRepo.save(utilisateur2);
            
            //model.addAttribute("utilisateur",utilisateur);
        }
        
        return "redirect:/home";
    }
	
	
	@GetMapping("/delete/{id}")
	public String Supprimer(@PathVariable(value="id")Long userId, Model model) {
	User utilisateur = null;
		Optional<User> user=userRepo.findById(userId);
		// Optional permet de gérer les cas de bug, id non trouvé ou null ici... sinon l'appli crash!
		if(user.isPresent()) {
			utilisateur=user.get(); 
			// vient de Optional permet de 'get()' toutes les infos du user dont l'ID a été trouvé dans la BDD
			model.addAttribute("utilisateur",utilisateur);
			userRepo.delete(utilisateur);
		}
		return"redirect:/home";
}
	
	
	
	@GetMapping("/nav")
	public String Nav() {
		return "navbar";
	}
	
	@GetMapping("/about")
	public String About() {
		return "about";
	}
	
	public void findusers(Model model){
		List<User> users = userRepo.findAll();
		
		model.addAttribute("users", users);
		
		//return users;
	}
	
	
}
