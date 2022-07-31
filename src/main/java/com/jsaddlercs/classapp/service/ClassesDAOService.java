package com.jsaddlercs.classapp.service;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.jsaddlercs.classapp.db.ClassesDAO;
import com.jsaddlercs.classapp.model.ClassesModel;


@Service
@Profile({"dao"})
public class ClassesDAOService implements ClassesService {
	private ClassesDAO classesDao;

	public ClassesDAOService(ClassesDAO classesDao) {
		this.classesDao = classesDao;
	}
	
	
	public List<ClassesModel> getSENG2000Classes() { 
		return classesDao.findSENG2000();
	}

	public List<ClassesModel> getAllClasses() {
		return classesDao.findAll();
	}
}
