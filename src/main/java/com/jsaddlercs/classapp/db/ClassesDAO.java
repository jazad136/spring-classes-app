package com.jsaddlercs.classapp.db;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.jsaddlercs.classapp.model.ClassesModel;
@Component
@Profile({"dev", "repo", "dao"})
public class ClassesDAO {
	private MongoTemplate mongoTemplate;

	public ClassesDAO(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	/**
	 * This method needs to:
	 * search through all the available topics
	 */
	public List<ClassesModel> findBySyllabusTopic(String topicName) {
		Query q = new Query();
		Criteria cr = where("classesName").is("SENG 2000");
		q.addCriteria(cr);
		List<ClassesModel> models = mongoTemplate.find(q, ClassesModel.class);
		return models;
	}
	public List<ClassesModel> findSENG2000() { 
		Query q = new Query();
		Criteria cr = where("classesName").is("SENG 2000");
		q.addCriteria(cr);
		List<ClassesModel> models = mongoTemplate.find(q, ClassesModel.class);
		return models;
//		mongoTemplate.executeQuery(q, "classes", DocumentCallbackHandler.);
//		Criteria.where(topicName)
	}

	public List<ClassesModel> findAll() {
		List<ClassesModel> models = mongoTemplate.findAll(ClassesModel.class);
		return models;
	}
}
