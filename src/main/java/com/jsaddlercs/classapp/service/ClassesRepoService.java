package com.jsaddlercs.classapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsaddlercs.classapp.db.Repository;
import com.jsaddlercs.classapp.model.ClassesModel;

@Service
public class ClassesRepoService {
	public Repository classesRepo;
	
	public ClassesRepoService(Repository classesRepo) { 
		this.classesRepo = classesRepo;
	}
	public List<ClassesModel> getAllClasses() { 
		return classesRepo.findAll();
	}
}
