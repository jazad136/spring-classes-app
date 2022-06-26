package com.jsaddlercs.classapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class ClassesImported {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClassesImported.class);
	
	public List<ClassesModel> readClasses;
	
	public ClassesImported(Environment env, Repository classesRepository) { 
		String prop = env.getProperty("cap.importFile", "");
		ClassesFromFile cff;
		if(prop.isBlank()) 
			throw new RuntimeException("Import file (cap.importFile) was not found in Spring Boot properties");
		try { 
			ClassPathResource propRes = new ClassPathResource(prop);
			cff = ClassesFromFile.read(propRes.getFile());
		}
		catch(IOException e) { 
			throw new RuntimeException("Import file (cap.importFile) could not be read from the file system", e);
		}
		readClasses = new ArrayList<>(cff.classes);
		printImportedMessage(prop);
		classesRepository.saveAll(readClasses);
		LOG.warn("Saved {} classes to repository", readClasses.size());
	}
	
	public void printImportedMessage(String propsFilename) { 
		LOG.warn("{} classes read in from '{}'", readClasses.size(), propsFilename);
		readClasses.forEach(cls -> LOG.warn(cls.getClassesName()));
		LOG.warn("End classes imported");
	}
	public static class ClassesFromFile { 
		private List<ClassesModel> classes;
		
		public static ClassesFromFile read(File fileToImport) throws IOException { 
			ObjectMapper om = new ObjectMapper().setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			return om.readValue(fileToImport, new TypeReference<ClassesFromFile>() {} );
		}
	}
}
