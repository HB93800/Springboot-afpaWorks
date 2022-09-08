package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Employe;

public interface WorkerRepository extends JpaRepository<Employe,Long>{
	Employe findByEmail(String email);
	
}
