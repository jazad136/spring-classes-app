package com.jsaddlercs.classapp.db;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.jsaddlercs.classapp.model.ClassesModel;
import com.jsaddlercs.classapp.model.LinkTopicExposure;
import com.jsaddlercs.classapp.model.SyllabusLink;
@Component
@Profile({"dev", "repo", "dao"})
public class ClassesDAO {
	private MongoTemplate mongoTemplate;

	public ClassesDAO(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	/**
	 * This method needs to:
	 * search through all the available exposure lists for a "match" as defined in testForMatch
	 * if at least one match is found in the exposure list for that syllabus. 
	 * that syllabus listing is kept in the result. 
	 * otherwise, the syllabus listings are modified to keep only the classes with offerings (syllabus links) 
	 * matching the topic specified.
	 */
	public List<ClassesModel> findBySyllabusTopic(String topic) {
		List<ClassesModel> models = new LinkedList<>(findAll());
		List<ClassesModel> returnedModels = new LinkedList<>();
		for(ClassesModel md : models) { 
			List<SyllabusLink> mdLinks = md.getSyllabusLinks();
			Iterator<SyllabusLink> linkIts = mdLinks.iterator();
			while(linkIts.hasNext()) {
				SyllabusLink link = linkIts.next();
				if(!exposureMatches(link.getTopicExposure(), topic)) 
					linkIts.remove();
			}
			if(!mdLinks.isEmpty()) {// we can only match classes with syllabus links in this query.
				md.setSyllabusLinks(mdLinks);
				returnedModels.add(md);
			}
		}
		return returnedModels;
	}
	public static boolean exposureMatches(LinkTopicExposure lte, String topic) { 
		if(lte == null) { 
			return false;
		}
		return testForMatch(lte.getAlgorithms(), topic) 
			|| testForMatch(lte.getLanguages(), topic) 
			|| testForMatch(lte.getLanguages(), topic);
	}
	public static boolean testForMatch(List<String> inputList, String topic) { 
		String searchTopic = topic.toLowerCase();
		return inputList.stream().anyMatch(str -> str.toLowerCase().startsWith(searchTopic));
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
