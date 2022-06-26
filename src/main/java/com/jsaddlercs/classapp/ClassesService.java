package com.jsaddlercs.classapp;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClassesService {
	public Repository classesRepo;
	
	public ClassesService(Repository classesRepo) { 
		this.classesRepo = classesRepo;
	}
	public List<ClassesModel> getAllClasses() { 
		return classesRepo.findAll();
	}
}
