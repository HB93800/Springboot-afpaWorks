package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;




@Entity //en JAVA on parle d'entité
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Precision donnée pour laisser à spring boot de gérer l'id
	private Long id;
	
	@Size (min=5, message="Nom trop court") // pour imposer une taille 
	private String nom;
	private String prenom;
	
	
	@Column(unique=true) // pour donner un paramètre à la colonne
	@Email(message="Le mail n'est pas conforme") // donner une typologie au type mail avec message en cas d'erreur
	@NotEmpty(message="Le mail ne doit pas être vide")
	private String email;
	
	//Getters&Settes
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	
	
	
	
	
	
}
