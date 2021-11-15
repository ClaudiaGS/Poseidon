package com.nnk.springboot.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurvePointService;
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
@TestPropertySource(locations="classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurvePointServiceIT {
    @Autowired
    ICurvePointService curvePointService;
    
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
    public void getCurvePointListIT() {
        assertThat(asJsonString(curvePointService.getCurvePointList())).isEqualTo("[{\"id\":1,\"curveId\":1,\"asOfDate\":null,\"term\":11.0,\"value\":111.0,\"creationDate\":null},{\"id\":2,\"curveId\":2,\"asOfDate\":null,\"term\":22.0,\"value\":222.0,\"creationDate\":null}]");
    }
    
    @Test
    @Order(2)
    public void getCurvePointIT() {
        assertThat(asJsonString(curvePointService.getCurvePoint(1).get())).isEqualTo("{\"id\":1,\"curveId\":1,\"asOfDate\":null,\"term\":11.0,\"value\":111.0,\"creationDate\":null}");
    }
    @Test
    @Order(4)
    public void deleteCurvePointIT(){
        curvePointService.deleteCurvePoint(1);
        assertThat(asJsonString(curvePointService.getCurvePointList())).isEqualTo("[{\"id\":2,\"curveId\":2,\"asOfDate\":null,\"term\":2222.0,\"value\":222.0,\"creationDate\":null}]");
    }
    @Test
    @Order(3)
    public void updateCurvePointIT() {
        CurvePoint updatedCurvePoint = new CurvePoint();
        updatedCurvePoint = curvePointService.getCurvePoint(2).get();
        updatedCurvePoint.setTerm(2222.0);
        curvePointService.updateCurvePoint(updatedCurvePoint);
        assertThat(asJsonString(curvePointService.getCurvePoint(2).get())).isEqualTo("{\"id\":2,\"curveId\":2,\"asOfDate\":null,\"term\":2222.0,\"value\":222.0,\"creationDate\":null}");
    }
    @Test
    @Order(5)
    public void createCurvePointIT(){
        CurvePoint newCurve=new CurvePoint();
        newCurve.setCurveId(3);
        newCurve.setTerm(33.0);
        newCurve.setValue(333.0);
        curvePointService.createCurvePoint(newCurve);
        assertThat(asJsonString(curvePointService.getCurvePoint(3).get())).isEqualTo("{\"id\":3,\"curveId\":3,\"asOfDate\":null,\"term\":33.0,\"value\":333.0,\"creationDate\":null}");
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
