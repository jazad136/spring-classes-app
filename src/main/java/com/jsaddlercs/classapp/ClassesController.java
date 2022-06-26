package com.jsaddlercs.classapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ClassesController {
	
	private final ClassesService classesService;
	
	public ClassesController(ClassesService service) { this.classesService = service; } 
	
	
	@GetMapping 
	public String getAllClasses(Model model) { 
		model.addAttribute("classes", classesService.getAllClasses());
		return "class/viewer";
	}
}
