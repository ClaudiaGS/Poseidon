package com.nnk.springboot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.interfaces.IUserService;
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
public class UserServiceIT {
    @Autowired
    IUserService userService;
    
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
    public void getUserListIT() {
        assertThat(asJsonString(userService.getUsers())).isEqualTo("[{\"id\":1,\"username\":\"admin\",\"password\":\"$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa\",\"fullname\":\"Administrator\",\"role\":\"ADMIN\"},{\"id\":2,\"username\":\"user\",\"password\":\"$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa\",\"fullname\":\"User\",\"role\":\"USER\"}]");
    }

    @Test
    @Order(2)
    public void getUserIT() {
        assertThat(asJsonString(userService.getUser(1).get())).isEqualTo("{\"id\":1,\"username\":\"admin\",\"password\":\"$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa\",\"fullname\":\"Administrator\",\"role\":\"ADMIN\"}");
    }

    @Test
    @Order(4)
    public void deleteUserIT() {
        userService.deleteUser(1);
        assertThat(asJsonString(userService.getUsers())).isEqualTo("[{\"id\":2,\"username\":\"user\",\"password\":\"$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa\",\"fullname\":\"User\",\"role\":\"ADMIN\"}]");
    }

    @Test
    @Order(3)
    public void updateUserIT() {
        User updatedUser = new User();
        updatedUser = userService.getUser(2).get();
        updatedUser.setRole("ADMIN");
        userService.updateUser(updatedUser);
        assertThat(asJsonString(userService.getUser(2).get())).isEqualTo("{\"id\":2,\"username\":\"user\",\"password\":\"$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa\",\"fullname\":\"User\",\"role\":\"ADMIN\"}");
    }

    @Test
    @Order(5)
    public void createUserIT() {
        User newUser=new User();
        newUser.setFullname("fullname");
        newUser.setUsername("username");
        newUser.setPassword("$2y$10$ATVvj640nIt1QA8OXpo1f.qDyLS5YamrB2Zy43kafiesDNmDrLUHW");
        newUser.setRole("USER");
        userService.createUser(newUser);
        assertThat(asJsonString(userService.getUser(3).get())).isEqualTo("{\"id\":3,\"username\":\"username\",\"password\":\"$2y$10$ATVvj640nIt1QA8OXpo1f.qDyLS5YamrB2Zy43kafiesDNmDrLUHW\",\"fullname\":\"fullname\",\"role\":\"USER\"}");
    }
   
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
