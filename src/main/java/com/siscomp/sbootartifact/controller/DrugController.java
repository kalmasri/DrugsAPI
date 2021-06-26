package com.siscomp.sbootartifact.controller;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.siscomp.sbootartifact.model.Drug;
import com.siscomp.sbootartifact.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// if @RequestParam then the param tab has to have the value
// if @RequestBody then pass json obj, write in body tab
// if @PathVariable then pass in the URL
//test 3
//test 4

@RestController
@RequestMapping("/api/drugs")
public class DrugController {
    // inject dependensy for DrugService
    @Autowired
    DrugService drugService;

    @PostMapping
    public Drug saveDrug(@RequestBody Drug drug){
        return drugService.saveDrug(drug);
    }

    // this is not optional must provide an id so use PathVariable, otherwise use repsose param
    // id is same as pathvariable
    @GetMapping("/{id}")
    public Drug getDrugById(@PathVariable  String id){
        return drugService.findDrugByID(id);
    }

    // here the param is set in post man (id set in query params)
    @GetMapping
    public Drug getById(@RequestParam  String id){
        return drugService.findDrugByID(id);
    }

// below 2 PUT methods
    //http://localhost:8080/api/drugs
    // but have to give id in request body or dont
    @PutMapping
    public Drug updateDrug(@RequestBody Drug drug){
    return drugService.updateDrug(drug);
    }

    //or
    // Post man: http://localhost:8080/api/drugs/782345734587345345id
    @PutMapping("/{id}")
    public Drug updateDrug(@RequestBody Drug drug, @PathVariable  String id){
        return drugService.updateDrugById(drug, id);
    }

    // Get and search by other params
    @GetMapping("/all")
    public List<Drug> getAllDrugs(){
        return drugService.findAllDrugs();
    }
// search by Name
    @GetMapping("/search/name")
    public List<Drug> getDrugByName(@RequestParam String name){
        return drugService.findDrugByName(name);
    }
    // search by dose
    @GetMapping("/search/dose")
    public List<Drug> getDrugByDose(@RequestParam String dose){
        return drugService.findDrugByDose(dose);
    }
    // search by name or dose
    // here it is taking one variable and checking name or dose
    @GetMapping("/searchOR")
    public List<Drug> getDrugByNameOrDose(@RequestParam String txt){
        return drugService.findDrugByNameOrDose(txt,txt);
    }
    // add to the params tab the name mentioned and pass value
    @GetMapping("/searchAND")
    public List<Drug> getDrugByNameAndDose(@RequestParam String name, @RequestParam String dose){
        return drugService.findDrugByNameAndDose(name,dose);
    }
}
