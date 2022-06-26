package com.jsaddlercs.classapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<ClassesModel, Long>{
	
}
