package com.siscomp.sbootartifact.drug;

import com.siscomp.sbootartifact.controller.DrugController;
import com.siscomp.sbootartifact.model.Drug;
import com.siscomp.sbootartifact.repository.DrugRepository;
import com.siscomp.sbootartifact.service.DrugService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = DrugController.class)
//@WithM
public class DrugControllerTest {

    @MockBean
    DrugController drugController;

    @MockBean
    DrugService drugService;

    @MockBean
    DrugRepository drugRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllDrugs() throws Exception {
        Drug drug = new Drug();
        drug.setName("TEST");
        drug.setDose("33");

        String getUri = "/api/drugs/all";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(getUri)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }
}
