package com.jsaddlercs.classapp.webrequest;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsaddlercs.classapp.response.ClassesResponse;
import com.jsaddlercs.classapp.response.PingResponse;
import com.jsaddlercs.classapp.service.ClassesDAOService;
import com.jsaddlercs.classapp.service.ClassesRepoService;
import com.jsaddlercs.classapp.service.ClassesService;

@RestController
@RequestMapping(path="/api/base")
@CrossOrigin
public class ClassesRestController {
	private ClassesService classesService;

	public ClassesRestController(ClassesService classesService) {
		this.classesService = classesService;
	}
	
	@GetMapping(path="/ping")
	@ResponseStatus(HttpStatus.OK)
	public PingResponse ping() { 
		return new PingResponse(true);
	}
	
	@GetMapping("/classes")
	public ClassesResponse getAllClasses() { 
		return new ClassesResponse(classesService.getAllClasses());
	}
		
	@GetMapping("/classes/SENG2000")
	public ClassesResponse getSENG2000Classes() { 
		return new ClassesResponse(classesService.getSENG2000Classes());
	}
	
	@Profile("dao")
	@GetMapping("/classes/filter") 
	public ClassesResponse getClassesByTopicQuery(@RequestParam(name="query") String queryInput) { 
		ClassesDAOService daoS = (ClassesDAOService) classesService;
		if(queryInput.isBlank()) { 
			return new ClassesResponse(daoS.getAllClasses());
		}
		String query = daoS.checkQueryInput(queryInput);
		return new ClassesResponse(daoS.getClassesByTopicQuery(query));
	}
	@Profile("repo")
	@GetMapping("/classes/byYear/{year}")
	public ClassesResponse getClassesByYear(@PathVariable Integer year) { 
		ClassesRepoService repoS = (ClassesRepoService) classesService;
		return new ClassesResponse(repoS.getClassesByYear(year));
	}
	
}
