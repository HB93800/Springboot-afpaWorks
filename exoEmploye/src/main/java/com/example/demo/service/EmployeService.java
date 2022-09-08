package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.WorkerRepository;

@Service
public class EmployeService {
	
	@Autowired
	private WorkerRepository workerRepo;
	
	public void AjoutEmploye() {
		workerRepo.save();
	}

}
