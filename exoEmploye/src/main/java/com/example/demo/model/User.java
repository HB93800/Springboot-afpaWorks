package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Precision donnée pour laisser à spring boot de gérer l'id
	private Long id;
	
	@Size (min=5, message="Nom trop court") // pour imposer une taille 
	private String nom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
