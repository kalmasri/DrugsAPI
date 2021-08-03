package com.siscomp.sbootartifact.service;

import com.siscomp.sbootartifact.model.Drug;
import com.siscomp.sbootartifact.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// CRUD operations done here, for Drug
@Service
public class DrugService {
    // Its injects the dependecies in spring
   // lets inject our drug repo into our service
    // in drug repo we extend mongorepo that has methods and call them
    @Autowired
    DrugRepository drugRepository;

    // passing drug to create a drug obj in database
    public Drug saveDrug(Drug drug){
       return drugRepository.save(drug);
    }

    // findbyid return optional which means there might be no drug,
    // so passing incorrect id, then nothing found so thats why optional
    // so add orElse to give null
    public Drug findDrugByID(String id){
        return drugRepository.findById(id).orElse(null);
    }

    //we are using path varaible so pass id,
    public Drug updateDrugById(Drug drug, String id) {
        Drug d = drugRepository.findById(id).orElse(null);
        d.setName(drug.getName());
        d.setDose(drug.getDose());
        d.setTimeStamp(drug.getTimeStamp());
        return drugRepository.save(d);
    }

    //same as saveDrug but will be used for the PUT
    public Drug updateDrug(Drug drug) {
//        if(drug != null) {
//            String name = drug.getName();
//        }
        return drugRepository.save(drug);
    }

    public List<Drug> findAllDrugs() {
        List<Drug> drugList = drugRepository.findAll();
        return drugList;

    }

    public List<Drug> findDrugByName(String name) {
        List<Drug> drugList = drugRepository.findByName(name);
        return drugList;
    }

    public List<Drug> findDrugByDose(String dose) {
        List<Drug> drugList = drugRepository.findByDose(dose);
        return drugList;
    }

    public List<Drug> findDrugByNameOrDose(String name, String dose) {
        List<Drug> drugList = drugRepository.findByNameOrDose(name, dose);
        return drugList;
    }

    public List<Drug> findDrugByNameAndDose(String name, String dose) {
        List<Drug> drugList = drugRepository.findByNameAndDose(name, dose);
        return drugList;
    }

//create new obj - POST, update obj - PUT

}
