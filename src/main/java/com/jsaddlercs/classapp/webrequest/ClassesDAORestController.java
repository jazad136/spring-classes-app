package com.jsaddlercs.classapp.webrequest;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsaddlercs.classapp.response.ClassesResponse;
import com.jsaddlercs.classapp.response.PingResponse;
import com.jsaddlercs.classapp.service.ClassesDAOService;

@Profile({"dao"})
@RestController
@RequestMapping(path="/api/v2")
@CrossOrigin
public class ClassesDAORestController {
	
	private final ClassesDAOService classesService;
	
	public ClassesDAORestController(ClassesDAOService service) { this.classesService = service; } 
	
	@GetMapping(path="/ping")
	@ResponseStatus(HttpStatus.OK)
	public PingResponse ping() { 
		return new PingResponse(true);
	}
	
	@GetMapping("/classes")
	public ClassesResponse getAllClasses() { 
		return new ClassesResponse(classesService.getAllClasses());
	}
	
//	@GetMapping("/classes/byYear/{year}")
//	public ClassesResponse getClassesByYear(@PathVariable String year) { 
//		Integer check = classesService.checkYearInput(year);
//		return new ClassesResponse(classesService.getClassesByYear(check));
//	}
	
	
	@GetMapping("/classes/SENG2000")
	public ClassesResponse getSENG2000Classes() { 
		return new ClassesResponse(classesService.getSENG2000Classes());
	}
	
}
