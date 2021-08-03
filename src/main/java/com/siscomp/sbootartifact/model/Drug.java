package com.siscomp.sbootartifact.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//It will auto provide getter and setter
@Data
//created drugs collection
@Document(collection = "drugs")
public class Drug {
    //primary key of the collection, type string
    @Id
    private String id;
    private String name;
    private String dose;
    private String timeStamp;



}
