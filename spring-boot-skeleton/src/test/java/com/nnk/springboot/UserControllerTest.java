package com.nnk.springboot;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
@SpringBootTest(classes = UserController.class)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    UserRepository userRepository;
    
    
    List<User> users = new ArrayList<>();
    User user1 = new User();
    User user2 = new User();
    
    @BeforeEach()
    public void config() {

        user1.setId(1);
        user1.setFullname("fullname1");
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setRole("USER");
        user2.setId(2);
        user2.setFullname("fullname2");
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setRole("USER");
        users.add(user1);
        users.add(user2);
    }
    
    
    @Test
    public void homeTest() {
        try {
            when(userRepository.findAll()).thenReturn(users);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/user/list"))
                    .andExpect(model().attribute("users", equalTo(users)))
                    .andExpect(forwardedUrlTemplate("user/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addUserForm() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrlTemplate("user/add"));
    }
    
    @Test
    public void validateTest() {
        try {
            User user3=new User();
            user3.setId(3);
            user3.setFullname("fullname3");
            user3.setUsername("user3");
            user3.setPassword("password3");
            user3.setRole("USER");
            users.add(user3);
            
            when(userRepository.findAll()).thenReturn(users);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/user/validate"))
                    .andExpect(model().attribute("users", equalTo(users)))
                    .andExpect(redirectedUrl("/user/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void showUpdateFormTest() {
        try {
            when(userRepository.findById(1).get()).thenReturn((user1));
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/user/update/1"))
                    .andExpect(model().attribute("user", equalTo(user1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void updateUserTest() {
        try {
            
            users.get(0).setUsername("user11");
            when(userRepository.findAll()).thenReturn(users);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/user/update/1"))
                    .andExpect(model().attribute("users", equalTo(users)))
                    .andExpect(redirectedUrlTemplate("/user/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void deleteUserTest() {
        try {
            users.remove(1);
            when(userRepository.findAll()).thenReturn(users);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/user/delete/2"))
                    .andExpect(model().attribute("users", equalTo(users)))
                    .andExpect(redirectedUrlTemplate("/user/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
