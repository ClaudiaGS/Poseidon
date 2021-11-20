package com.nnk.springboot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITradeService;
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
public class TradeServiceIT {
    @Autowired
    ITradeService tradeService;
    
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
    public void getTradeListIT() {
        assertThat(asJsonString(tradeService.getTrades())).isEqualTo("[{\"tradeId\":1,\"account\":\"account1\",\"type\":\"type1\",\"buyQuantity\":1000.0,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null},{\"tradeId\":2,\"account\":\"account2\",\"type\":\"type2\",\"buyQuantity\":2000.0,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}]");
    }
    
    @Test
    @Order(2)
    public void getTradeIT() {
        assertThat(asJsonString(tradeService.getTrade(1).get())).isEqualTo("{\"tradeId\":1,\"account\":\"account1\",\"type\":\"type1\",\"buyQuantity\":1000.0,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}");
    }

    @Test
    @Order(4)
    public void deleteTradeIT() {
        tradeService.deleteTrade(1);
        assertThat(asJsonString(tradeService.getTrades())).isEqualTo("[{\"tradeId\":2,\"account\":\"account2\",\"type\":\"type2\",\"buyQuantity\":2222.0,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}]");
    }

    @Test
    @Order(3)
    public void updateTradeIT() {
        Trade updatedTrade=new Trade();
        updatedTrade=tradeService.getTrade(2).get();
        updatedTrade.setBuyQuantity(2222.0);
        tradeService.updateTrade(updatedTrade);
        assertThat(asJsonString(tradeService.getTrade(2).get())).isEqualTo("{\"tradeId\":2,\"account\":\"account2\",\"type\":\"type2\",\"buyQuantity\":2222.0,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}");
    }

    @Test
    @Order(5)
    public void createTradeIT() {
        Trade newTrade=new Trade();
        newTrade.setAccount("account3");
        newTrade.setType("type3");
        newTrade.setBuyQuantity(3000.0);
        tradeService.createTrade(newTrade);
        assertThat(asJsonString(tradeService.getTrade(3).get())).isEqualTo("{\"tradeId\":3,\"account\":\"account3\",\"type\":\"type3\",\"buyQuantity\":3000.0,\"sellQuantity\":null,\"buyPrice\":null,\"sellPrice\":null,\"benchmark\":null,\"tradeDate\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null}");
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
