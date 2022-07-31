package com.jsaddlercs.classapp.db;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jsaddlercs.classapp.model.ClassesModel;

@Repository
public interface PagingClassesRepository extends ClassesRepository, PagingAndSortingRepository<ClassesModel, Long>{

}
