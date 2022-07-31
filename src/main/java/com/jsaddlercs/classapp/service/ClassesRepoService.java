package com.jsaddlercs.classapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.jsaddlercs.classapp.db.ClassesRepository;
import com.jsaddlercs.classapp.exception.CLAException;
import com.jsaddlercs.classapp.model.ClassesModel;
import com.jsaddlercs.classapp.model.SyllabusLink;

@Service
@Profile({"dev", "repo"})
public class ClassesRepoService implements ClassesService {
	public ClassesRepository classesRepo;
	
	public ClassesRepoService(ClassesRepository classesRepo) { 
		this.classesRepo = classesRepo;
	}
	public List<ClassesModel> getAllClasses() { 
		return classesRepo.findAll();
	}
	public Integer checkYearInput(String year) {
		if(year == null || year.isBlank()) { 
			throw new CLAParamNotExistsException("Year parameter was not passed.");
		}
		
		return Integer.parseInt(year);
	}
	
	public List<ClassesModel> getClassesByYear(Integer check) {
		List<ClassesModel> out = new ArrayList<>();
		
		classesLoop:
		for(ClassesModel cls : classesRepo.findAll()) { 
			for(SyllabusLink sl : cls.getSyllabusLinks()) { 
				if(sl.getIntegerOfferingYear().equals(check)) {
					out.add(cls);
					continue classesLoop;
				}
			}
		}
		return out;
	}
	public List<ClassesModel> getSENG2000Classes() {
		return classesRepo.findAllByClassesName("SENG 2000");
	}
	
	@SuppressWarnings("serial")
	public static class CLAParamNotExistsException extends CLAException { 
		public CLAParamNotExistsException(String message) { 
			super(message);
		}
	}

}
