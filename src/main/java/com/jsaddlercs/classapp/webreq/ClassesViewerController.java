package com.jsaddlercs.classapp.webreq;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsaddlercs.classapp.service.ClassesRepoService;

@Controller
@RequestMapping()
public class ClassesViewerController {
	
	private final ClassesRepoService classesService;
	
	public ClassesViewerController(ClassesRepoService service) { this.classesService = service; } 
	
	
	@GetMapping 
	public String getAllClasses(Model model) { 
		model.addAttribute("classes", classesService.getAllClasses());
		return "class/viewer";
	}
}
