package com.nnk.springboot;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.interfaces.IRatingService;
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
@SpringBootTest(classes = RatingController.class)
public class RatingControllerTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    IRatingService ratingService;
    
    
    List<Rating> ratings = new ArrayList<>();
    Rating rating1=new Rating();
    Rating rating2=new Rating();
    
    @BeforeEach()
    public void config() {

        rating1.setId(1);
        rating1.setMoodysRating("moodys1");
        rating1.setSandPRating("sandPR1");
        rating1.setOrderNumber(1);
        rating2.setId(2);
        rating2.setMoodysRating("moodys2");
        rating2.setSandPRating("sandPR2");
        rating2.setOrderNumber(2);
        
        ratings.add(rating1);
        ratings.add(rating2);
        
    }
    
    
    @Test
    public void homeTest() {
        try {
            when(ratingService.getRatingList()).thenReturn(ratings);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/rating/list"))
                    .andExpect(model().attribute("ratings", equalTo(ratings)))
                    .andExpect(forwardedUrlTemplate("rating/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addRatingForm() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrlTemplate("rating/add"));
    }

    @Test
    public void validateTest() {
        try {
            Rating rating3=new Rating();
            rating3.setId(3);
            rating3.setMoodysRating("moodys3");
            rating3.setSandPRating("sandPR3");
            rating3.setOrderNumber(3);
            when(ratingService.getRatingList()).thenReturn(ratings);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/rating/validate"))
                    .andExpect(model().attribute("ratings", equalTo(ratings)))
                    .andExpect(redirectedUrl("/rating/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void showUpdateFormTest() {
        try {
            when(ratingService.getRating(1).get()).thenReturn((rating1));
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/rating/update/1"))
                    .andExpect(model().attribute("rating", equalTo(rating1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateRatingTest() {
        try {

            ratings.get(0).setOrderNumber(11);
            when(ratingService.getRatingList()).thenReturn(ratings);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/rating/update/1"))
                    .andExpect(model().attribute("ratings", equalTo(ratings)))
                    .andExpect(redirectedUrlTemplate("/rating/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteRatingTest() {
        try {
            ratings.remove(1);
            when(ratingService.getRatingList()).thenReturn(ratings);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/rating/delete/2"))
                    .andExpect(model().attribute("ratings", equalTo(ratings)))
                    .andExpect(redirectedUrlTemplate("/rating/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}


