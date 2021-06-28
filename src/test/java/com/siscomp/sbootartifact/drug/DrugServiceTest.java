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


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
public class DrugServiceTest {

    @Autowired
    DrugService drugService;

    @MockBean
    DrugRepository drugRepository;

    @Test
    public void saveDrugTest(){
        Drug drug = new Drug();
        drug.setName("TEST");
        drug.setDose("33");

        Mockito.when(drugRepository.save(drug)).thenReturn(drug);
        Assert.assertEquals(drug, drugService.saveDrug(drug));

    }

}
