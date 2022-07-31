package com.jsaddlercs.classapp.webrequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsaddlercs.classapp.response.ClassesResponse;
import com.jsaddlercs.classapp.response.PingResponse;
import com.jsaddlercs.classapp.service.ClassesService;

@RestController
@RequestMapping(path="/api/base")
@CrossOrigin
public class ClassesController {
	private ClassesService classesService;

	public ClassesController(ClassesService classesService) {
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
	
}
