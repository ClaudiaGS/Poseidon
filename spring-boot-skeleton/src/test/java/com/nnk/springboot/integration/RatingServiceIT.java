package com.nnk.springboot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.interfaces.IRatingService;
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
public class RatingServiceIT {
    @Autowired
    IRatingService ratingService;
    
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
    public void getRatingListIT() {
        assertThat(asJsonString(ratingService.getRatingList())).isEqualTo("[{\"id\":1,\"moodysRating\":\"moodys1\",\"sandPRating\":\"sandPR1\",\"fitchRating\":\"fitch1\",\"orderNumber\":1},{\"id\":2,\"moodysRating\":\"moodys2\",\"sandPRating\":\"sandPR2\",\"fitchRating\":\"fitch2\",\"orderNumber\":2}]");
    }
    
    @Test
    @Order(2)
    public void getRatingIT() {
        assertThat(asJsonString(ratingService.getRating(1).get())).isEqualTo("{\"id\":1,\"moodysRating\":\"moodys1\",\"sandPRating\":\"sandPR1\",\"fitchRating\":\"fitch1\",\"orderNumber\":1}");
    }
    
    
    @Test
    @Order(4)
    public void deleteRatingIT() {
        ratingService.deleteRating(1);
        assertThat(asJsonString(ratingService.getRatingList())).isEqualTo("[{\"id\":2,\"moodysRating\":\"moodys2\",\"sandPRating\":\"sandPR2\",\"fitchRating\":\"fitch2\",\"orderNumber\":22}]");
    }
    
    @Test
    @Order(3)
    public void updateRatingIT() {
        Rating updatedRating = new Rating();
        updatedRating = ratingService.getRating(2).get();
        updatedRating.setOrderNumber(22);
        ratingService.updateRating(updatedRating);
        assertThat(asJsonString(ratingService.getRating(2).get())).isEqualTo("{\"id\":2,\"moodysRating\":\"moodys2\",\"sandPRating\":\"sandPR2\",\"fitchRating\":\"fitch2\",\"orderNumber\":22}");
    }
    
    @Test
    @Order(5)
    public void createRatingIT() {
        Rating newRating = new Rating();
        newRating.setMoodysRating("moodys3");
        newRating.setSandPRating("sandPR3");
        newRating.setFitchRating("fitch3");
        newRating.setOrderNumber(3);
        ratingService.createRating(newRating);
        assertThat(asJsonString(ratingService.getRating(3).get())).isEqualTo("{\"id\":3,\"moodysRating\":\"moodys3\",\"sandPRating\":\"sandPR3\",\"fitchRating\":\"fitch3\",\"orderNumber\":3}");
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
