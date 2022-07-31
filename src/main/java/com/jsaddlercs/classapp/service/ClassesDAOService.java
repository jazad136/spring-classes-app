package com.jsaddlercs.classapp.service;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.jsaddlercs.classapp.db.ClassesDAO;
import com.jsaddlercs.classapp.exception.CLAException;
import com.jsaddlercs.classapp.model.ClassesModel;


@Service
@Profile({"dao"})
public class ClassesDAOService implements ClassesService {
	private ClassesDAO classesDao;

	public ClassesDAOService(ClassesDAO classesDao) {
		this.classesDao = classesDao;
	}
	
	public Integer checkYearInput(String year) {
		if(year == null || year.isBlank()) { 
			throw new CLAParamNotExistsException("Year parameter was not passed.");
		}
		return Integer.parseInt(year);
	}
	
	public String checkQueryInput(String query) { 
		if(query == null || query.isBlank()) { 
			throw new CLAParamNotExistsException("Query parameter was not passed");
		}
		return query;
	}
	
	public List<ClassesModel> getClassesByTopicQuery(String topicQuery) { 
		return classesDao.findBySyllabusTopic(topicQuery);
	}
	
	public List<ClassesModel> getSENG2000Classes() { 
		return classesDao.findSENG2000();
	}

	public List<ClassesModel> getAllClasses() {
		return classesDao.findAll();
	}
	
	@SuppressWarnings("serial")
	public static class CLAParamNotExistsException extends CLAException { 
		public CLAParamNotExistsException(String message) { 
			super(message);
		}
	}
}
