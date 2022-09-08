package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Employe {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Precision donnée pour laisser à spring boot de gérer l'id
	private Long id;
	
	@Size (min=3, message="Nom trop court") // pour imposer une taille 
	private String nom;
	private String prenom;
	
	
	@Column(unique=true) // pour donner un paramètre à la colonne
	@Email(message="Le mail n'est pas conforme") // donner une typologie au type mail avec message en cas d'erreur
	@NotEmpty(message="Le mail ne doit pas être vide")
	private String email;
	
	@ManyToOne  //Pour créer les cardinalités ici 'Many to One' et donc en BDD > les FOREIGN KEYS
	private Fonction fonction;
	
	
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
	
	public Fonction getFonction() {
		return fonction;
	}
	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", fonction="
				+ fonction + "]";
	}
	
	
}
