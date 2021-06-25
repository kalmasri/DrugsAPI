package com.siscomp.sbootartifact.repository;

import com.siscomp.sbootartifact.model.Drug;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//  the repos will communicate with database for Drug collection,
//  our primary key is string so we add that
// interface provides us with default methods (ex findAll())
@Repository
public interface DrugRepository extends MongoRepository<Drug,String> {

    // findByName will generate a SQL query like select * from drug where name = 'x'
    // these are called JP Queries
    List<Drug> findByName(String name);

    List<Drug> findByDose(String dose);

    List<Drug> findByNameOrDose(String name, String dose);

    List<Drug> findByNameAndDose(String name, String dose);

}
