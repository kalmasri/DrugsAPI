package com.siscomp.sbootartifact.drug;


import com.siscomp.sbootartifact.model.Drug;
import com.siscomp.sbootartifact.repository.DrugRepository;
import com.siscomp.sbootartifact.service.DrugService;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
public class DrugServiceTest {

    @MockBean
    DrugService drugService;

    @MockBean
    DrugRepository drugRepository;

    @Test
    public void saveDrug(){
        Drug drug = new Drug();
        drug.setName("TEST");
        drug.setDose("33");

        Mockito.when(drugRepository.save(drug)).thenReturn(drug);
        Assert.assertEquals(drug, drugService.saveDrug(drug));

    }

    @Test
    public void findByID(){
        Drug drug = new Drug();
        drug.setId("60d49c34bf4a0f1ad73xxxxx");
        drug.setName("TEST2");
        drug.setDose("22");

        //findbyId is returning optional value
        // whatever the value is it checks unit test (ex: if (x !=null)
        // for the method findById, whatever the value is, please return x = drug
        Mockito.when(drugRepository.findById("60d49c34bf4a0f1ad73xxxxx")).thenReturn(java.util.Optional.of(drug));
        Assert.assertEquals(drug, drugService.findDrugByID("60d49c34bf4a0f1ad73xxxxx"));

    }

    @Test
    public void findDrugByNameOrDose(){
        Drug drug1 = new Drug();
        drug1.setId("60d49c34bf4a0f1ad73xxxxx");
        drug1.setName("one");
        drug1.setDose("24mg");

        Drug drug2 = new Drug();
        //drug2.setId("60d49c34bf4a0f1ad73xxxxx");
        drug2.setName("two");
        drug2.setDose("23mg");

        Drug drug3 = new Drug();
        //drug2.setId("60d49c34bf4a0f1ad73xxxxx");
        drug3.setName("three");
        drug3.setDose("23mg");

        System.out.println(drug1.getId());
        System.out.println(drug2.getId());

        List<Drug> list = new ArrayList<>();
        list.add(drug2);
        list.add(drug3);

        List<Drug> drug1List = new ArrayList<>();
        drug1List.add(drug1);

        // passing "one" and "22mg", if data return list otherwise empty list
        // if there is an error it will throw exp and not return drug1list
        Mockito.when(drugRepository.findByNameOrDose("xxcfsdf","sdfsdffsd")).thenReturn(drug1List);

        List<Drug> listCompare = drugService.findDrugByNameOrDose(drug1.getName(),drug1.getDose());
        Assert.assertEquals(drug1List.size(), listCompare.size());

    }

    @Test
    public void updateDrugById(){
        Drug drug1 = new Drug();
        drug1.setId("60d49c34bf4a0f1ad73xxxxx");
        drug1.setName("one");
        drug1.setDose("24mg");

        Mockito.when(drugRepository.save(drug1)).thenReturn(drug1);

      //  Drug drug2 = drugService.findDrugByID("anything");
        Drug drug3 = drugService.saveDrug(drug1);

        Assert.assertEquals(drug1, drug3);

    }

}
