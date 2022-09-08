package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fonction {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Precision donnée pour laisser à spring boot de gérer l'id
	private Long id;

	
	//Getter&Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
