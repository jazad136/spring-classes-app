package com.jsaddlercs.classapp.webrequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsaddlercs.classapp.service.ClassesService;

@Controller
@RequestMapping()
public class ClassesViewerController {
	
	private final ClassesService classesService;
	
	public ClassesViewerController(ClassesService service) { this.classesService = service; } 
	
	
	@GetMapping 
	public String getAllClasses(Model model) { 
		model.addAttribute("classes", classesService.getAllClasses());
		return "class/viewer";
	}
}
