package com.nnk.springboot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.interfaces.IBidListService;
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

//@Import(TestConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//@SpringBootTest(properties = {"spring.datesourcetest.url=jdbc:mysql://localhost:3306/demoTest"})
//@Sql({"config/resources/dataTest.sql"})
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class BidListServiceIT {
    @Autowired
    IBidListService bidListService;
    
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
    public void getBidListsIT() {
        assertThat(asJsonString(bidListService.getBidLists())).isEqualTo("[{\"account\":\"account1\",\"type\":\"type1\",\"bidQuantity\":100.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":1},{\"account\":\"account2\",\"type\":\"type2\",\"bidQuantity\":200.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":2}]");
    }
    @Test
    @Order(2)
    public void getBidListIT(){
        assertThat(asJsonString(bidListService.getBidList(1).get())).isEqualTo("{\"account\":\"account1\",\"type\":\"type1\",\"bidQuantity\":100.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":1}");
    }
    @Test
    @Order(5)
    public void deleteBidListIT(){
        bidListService.deleteBidList(1);
        assertThat(asJsonString(bidListService.getBidLists())).isEqualTo("[{\"account\":\"account2\",\"type\":\"type2\",\"bidQuantity\":222.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":2},{\"account\":\"account3\",\"type\":\"type3\",\"bidQuantity\":300.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":3}]");
    }
    @Test
    @Order(4)
    public void updateBidListIT(){
        BidList updatedBid=new BidList();
        updatedBid=bidListService.getBidList(2).get();
        updatedBid.setBidQuantity(222.0);
        bidListService.updateBidList(updatedBid);
        assertThat(asJsonString(bidListService.getBidList(2).get())).isEqualTo("{\"account\":\"account2\",\"type\":\"type2\",\"bidQuantity\":222.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":2}");
    }
    @Test
    @Order(3)
    public void createBidListIT(){
        BidList newBid= new BidList();
        newBid.setAccount("account3");
        newBid.setType("type3");
        newBid.setBidQuantity(300.0);
        bidListService.createBidList(newBid);
        assertThat(asJsonString(bidListService.getBidList(3).get())).isEqualTo("{\"account\":\"account3\",\"type\":\"type3\",\"bidQuantity\":300.0,\"askQuantity\":null,\"bid\":null,\"ask\":null,\"benchmark\":null,\"bidListDate\":null,\"commentary\":null,\"security\":null,\"status\":null,\"trader\":null,\"book\":null,\"creationName\":null,\"creationDate\":null,\"revisionName\":null,\"revisionDate\":null,\"dealName\":null,\"dealType\":null,\"sourceListId\":null,\"side\":null,\"bidListId\":3}");
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}