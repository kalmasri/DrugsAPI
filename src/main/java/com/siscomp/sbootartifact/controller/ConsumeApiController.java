package com.siscomp.sbootartifact.controller;

import com.siscomp.sbootartifact.model.Drug;
import com.siscomp.sbootartifact.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

// the other one /api/drugs will return drug object
@RestController
@RequestMapping("/consume")
public class ConsumeApiController {

    @Autowired
    DrugService drugService;

    @GetMapping("/fromProducer/{id}")
    public Drug consumeDrug(@PathVariable String id){
        // restTemplate is for consuming API (POST, GET), to call rest API
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8087/api/drugs/"+id;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //httpHeaders consume httpHeaders
        HttpEntity<String>  httpEntity = new HttpEntity<String>(httpHeaders);
        //restTemplate.exchange (which url to consume, method type, http headers, return type)

        ResponseEntity<Drug> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity ,Drug.class);
        Drug drugNew = responseEntity.getBody();

        return drugNew;
    }

    @GetMapping("/fromConsumer/{id}")
    public Drug viewDrug(@PathVariable String id){
        return drugService.findDrugByID(id);
    }


    //here we get from siscomp and save into mangotest database, consume from another service and save data
    @PostMapping
    public Drug consumeAndPostDrug(){
        // restTemplate is for consuming API (POST, GET), to call rest API
        RestTemplate restTemplate = new RestTemplate();
        String id = "6101551d2ce9e162868aa490";
        String url = "http://localhost:8087/api/drugs/"+id;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //httpHeaders consume httpHeaders
        HttpEntity<String>  httpEntity = new HttpEntity<String>(httpHeaders);
        //restTemplate.exchange (which url to consume, method type, http headers, return type)

        ResponseEntity<Drug> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity ,Drug.class);
        Drug drugNew = responseEntity.getBody();
        //drugNew.setTimeStamp("03/03/2021 09:32:41");

        drugService.saveDrug(drugNew);
        return drugNew;
    }

    // if you have 2 mapping POST with same URL you have to define path
    @PostMapping("/post/{id}")
    public Drug consumeOverwriteAndPostDrug(@PathVariable String id){
        // restTemplate is for consuming API (POST, GET), to call rest API
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8087/api/drugs/"+id;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //httpHeaders consume httpHeaders
        HttpEntity<String>  httpEntity = new HttpEntity<String>(httpHeaders);
        //restTemplate.exchange (which url to consume, method type, http headers, return type)

        ResponseEntity<Drug> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity ,Drug.class);
        Drug drugNew = responseEntity.getBody();
        drugNew.setId(null);
        drugNew.setTimeStamp("05/01/2020 09:32:41");
        Drug drugReturned = drugService.saveDrug(drugNew);
        return drugReturned;
    }

    // POST creates new Obj, PUT updates existing
    // if no ID exist -> both will create a new obj
    // if ID does exist -> POST will do nothing, PUT will Overwrite
//    @PutMapping("/put/{id}")
//    public Drug consumeAndPutDrug(@PathVariable String id){
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8087/api/drugs/"+id;
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String>  httpEntity = new HttpEntity<String>(httpHeaders);
//
//        ResponseEntity<Drug> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity ,Drug.class);
//        Drug drugNew = responseEntity.getBody();
//
//        // if set null ID it will create new obj (like POST)
//        drugNew.setName("Xyionfor");
//        drugNew.setTimeStamp("03/01/2020 07:37:11");
//        Drug drugReturn =drugService.updateDrug(drugNew);
//
//        return drugReturn;
//    }
}
