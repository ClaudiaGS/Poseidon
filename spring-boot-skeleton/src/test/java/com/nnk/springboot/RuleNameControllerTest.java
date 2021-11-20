package com.nnk.springboot;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.interfaces.IRuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RuleNameController.class)
public class RuleNameControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    IRuleNameService ruleNameService;
    
    
    List<RuleName> ruleNames = new ArrayList<>();
    RuleName ruleName1 = new RuleName();
    RuleName ruleName2 = new RuleName();
    
    @BeforeEach()
    public void config() {
        ruleName1.setId(1);
        ruleName1.setName("name1");
        ruleName1.setDescription("description1");
        ruleName1.setJson("json1");
        ruleName1.setTemplate("template1");
        ruleName1.setSqlStr("sql1");
        ruleName1.setSqlPart("sqlPart1");
    
        ruleName2.setId(2);
        ruleName2.setName("name2");
        ruleName2.setDescription("description2");
        ruleName2.setJson("json2");
        ruleName2.setTemplate("template2");
        ruleName2.setSqlStr("sql2");
        ruleName2.setSqlPart("sqlPart2");
        
        ruleNames.add(ruleName1);
        ruleNames.add(ruleName2);
    }
    
    
    @Test
    public void homeTest() {
        try {
            when(ruleNameService.getRuleNameList()).thenReturn(ruleNames);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/ruleName/list"))
                    .andExpect(model().attribute("rules", equalTo(ruleNames)))
                    .andExpect(forwardedUrlTemplate("ruleName/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addRuleForm() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrlTemplate("ruleName/add"));
    }

    @Test
    public void validateTest() {
        try {
            RuleName ruleName3=new RuleName();
            ruleName3.setId(3);
            ruleName3.setName("name3");
            ruleName3.setDescription("description3");
            ruleName3.setJson("json3");
            ruleName3.setTemplate("template3");
            ruleName3.setSqlStr("sql3");
            ruleName3.setSqlPart("sqlPart3");
            
            ruleNames.add(ruleName3);
            when(ruleNameService.getRuleNameList()).thenReturn(ruleNames);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/ruleName/validate"))
                    .andExpect(model().attribute("rules", equalTo(ruleNames)))
                    .andExpect(redirectedUrl("/ruleName/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void showUpdateFormTest() {
        try {
            when(ruleNameService.getRuleName(1).get()).thenReturn((ruleName1));
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/ruleName/update/1"))
                    .andExpect(model().attribute("ruleName", equalTo(ruleName1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateRuleNameTest() {
        try {

            ruleNames.get(0).setName("name11");
            when(ruleNameService.getRuleNameList()).thenReturn(ruleNames);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/ruleName/update/1"))
                    .andExpect(model().attribute("rules", equalTo(ruleNames)))
                    .andExpect(redirectedUrlTemplate("/ruleName/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteRuleNameTest() {
        try {
            ruleNames.remove(1);
            when(ruleNameService.getRuleNameList()).thenReturn(ruleNames);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/ruleName/delete/2"))
                    .andExpect(model().attribute("rules", equalTo(ruleNames)))
                    .andExpect(redirectedUrlTemplate("/ruleName/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}


