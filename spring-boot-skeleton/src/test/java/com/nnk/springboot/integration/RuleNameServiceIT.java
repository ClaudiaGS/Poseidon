package com.nnk.springboot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.interfaces.IRuleNameService;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RuleNameServiceIT {
    @Autowired
    IRuleNameService ruleNameService;
    
    @Autowired
    DataSource dataBaseTest;
    
    @BeforeAll
    public void config() {
        Connection con = null;
        ScriptRunner sr = null;
        Reader reader = null;
        try {
            con = dataBaseTest.getConnection();
            sr = new ScriptRunner(con);
            reader = new BufferedReader(new FileReader("F:\\OPENCLASSROOMS\\PROJET 7\\PoseidonGit\\spring-boot-skeleton\\src\\test\\java\\com\\nnk\\springboot\\integration\\config\\resources\\dataTest.sql"));
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sr.runScript(reader);
        
    }
    
    
    @Test
    @Order(1)
    public void getRuleNameListIT() {
        assertThat(asJsonString(ruleNameService.getRuleNameList())).isEqualTo("[{\"id\":1,\"name\":\"name1\",\"description\":\"description1\",\"json\":\"json1\",\"template\":\"template1\",\"sqlStr\":\"sql1\",\"sqlPart\":\"sqlPart1\"},{\"id\":2,\"name\":\"name2\",\"description\":\"description2\",\"json\":\"json2\",\"template\":\"template2\",\"sqlStr\":\"sql2\",\"sqlPart\":\"sqlPart2\"}]");
    }

    @Test
    @Order(2)
    public void getRuleNameIT() {
        assertThat(asJsonString(ruleNameService.getRuleName(1).get())).isEqualTo("{\"id\":1,\"name\":\"name1\",\"description\":\"description1\",\"json\":\"json1\",\"template\":\"template1\",\"sqlStr\":\"sql1\",\"sqlPart\":\"sqlPart1\"}");
    }


    @Test
    @Order(4)
    public void deleteRuleNameIT() {
        ruleNameService.deleteRuleName(1);
        assertThat(asJsonString(ruleNameService.getRuleNameList())).isEqualTo("[{\"id\":2,\"name\":\"name2\",\"description\":\"description22\",\"json\":\"json2\",\"template\":\"template2\",\"sqlStr\":\"sql2\",\"sqlPart\":\"sqlPart2\"}]");
    }

    @Test
    @Order(3)
    public void updateRuleNameIT() {
        RuleName updatedRuleName = new RuleName();
        updatedRuleName = ruleNameService.getRuleName(2).get();
        updatedRuleName.setDescription("description22");
        ruleNameService.updateRuleName(updatedRuleName);
        assertThat(asJsonString(ruleNameService.getRuleName(2).get())).isEqualTo("{\"id\":2,\"name\":\"name2\",\"description\":\"description22\",\"json\":\"json2\",\"template\":\"template2\",\"sqlStr\":\"sql2\",\"sqlPart\":\"sqlPart2\"}");
    }

    @Test
    @Order(5)
    public void createRuleNameIT() {
        RuleName newRuleName = new RuleName();
        newRuleName.setName("name3");
        newRuleName.setDescription("description3");
        newRuleName.setJson("json3");
        newRuleName.setTemplate("template3");
        newRuleName.setSqlStr("sql3");
        newRuleName.setSqlPart("sqlPart3");
        ruleNameService.createRuleName(newRuleName);
        assertThat(asJsonString(ruleNameService.getRuleName(3).get())).isEqualTo("{\"id\":3,\"name\":\"name3\",\"description\":\"description3\",\"json\":\"json3\",\"template\":\"template3\",\"sqlStr\":\"sql3\",\"sqlPart\":\"sqlPart3\"}");
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
