package com.jsaddlercs.classapp.webrequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsaddlercs.classapp.response.ClassesResponse;
import com.jsaddlercs.classapp.service.ClassesRepoService;

@RestController("/api")
@RequestMapping
public class ClassesRestController {
	
	private final ClassesRepoService classesService;
	
	public ClassesRestController(ClassesRepoService service) { this.classesService = service; } 
	
	
	@GetMapping("/classes")
	public ClassesResponse getAllClasses() { 
		return new ClassesResponse(classesService.getAllClasses());
	}
	
	@GetMapping("/classes/{year}")
	public ClassesResponse getClassesByYear(@PathVariable String year) { 
		Integer check = classesService.checkYearInput(year);
		return new ClassesResponse(classesService.getClassesByYear(check));
	}
	
}
