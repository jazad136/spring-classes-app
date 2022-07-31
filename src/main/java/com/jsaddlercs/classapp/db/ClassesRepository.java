package com.jsaddlercs.classapp.db;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jsaddlercs.classapp.model.ClassesModel;

@Primary
@Repository
public interface ClassesRepository extends MongoRepository<ClassesModel, Long> {
	List<ClassesModel> findAllByClassesName(String name);
}
