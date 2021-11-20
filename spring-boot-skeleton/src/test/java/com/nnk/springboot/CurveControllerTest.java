package com.nnk.springboot;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurvePointService;
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
@SpringBootTest(classes = CurveController.class)
public class CurveControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    ICurvePointService curvePointService;
    
    
    List<CurvePoint> curvePoints = new ArrayList<>();
    CurvePoint curvePoint1 = new CurvePoint();
    CurvePoint curvePoint2 = new CurvePoint();
    
    @BeforeEach()
    public void config() {
        
        curvePoint1.setId(1);
        curvePoint1.setCurveId(1);
        curvePoint1.setTerm(11.0);
        curvePoint1.setValue(111.0);
        curvePoint2.setId(2);
        curvePoint2.setCurveId(2);
        curvePoint2.setTerm(22.0);
        curvePoint2.setValue(222.0);
        curvePoints.add(curvePoint1);
        curvePoints.add(curvePoint2);
    }
    
    
    @Test
    public void homeTest() {
        try {
            when(curvePointService.getCurvePointList()).thenReturn(curvePoints);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/curvePoint/list"))
                    .andExpect(model().attribute("curvePointList", equalTo(curvePoints)))
                    .andExpect(forwardedUrlTemplate("curvePoint/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addCurveFormTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrlTemplate("curvePoint/add"));
    }
    
    @Test
    public void validateTest() {
        try {
            CurvePoint curvePoint3 = new CurvePoint();
            curvePoint3.setId(3);
            curvePoint3.setCurveId(3);
            curvePoint3.setTerm(33.0);
            curvePoint3.setValue(333.0);
            curvePoints.add(curvePoint3);
            when(curvePointService.getCurvePointList()).thenReturn(curvePoints);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/curvePoint/validate"))
                    .andExpect(model().attribute("curvePointList", equalTo(curvePoints)))
                    .andExpect(redirectedUrl("/curvePoint/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void showUpdateFormTest() {
        try {
            when(curvePointService.getCurvePoint(1).get()).thenReturn((curvePoint1));
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/curvePoint/update/1"))
                    .andExpect(model().attribute("curvePoint", equalTo(curvePoint1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void updateCurvePointTest() {
        try {
            
            curvePoints.get(0).setValue(100.0);
            when(curvePointService.getCurvePointList()).thenReturn(curvePoints);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/curvePoint/update/1"))
                    .andExpect(model().attribute("curvePointList", equalTo(curvePoints)))
                    .andExpect(redirectedUrlTemplate("/curvePoint/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void deleteCurvePointTest() {
        try {
            curvePoints.remove(1);
            when(curvePointService.getCurvePointList()).thenReturn(curvePoints);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/curvePoint/delete/2"))
                    .andExpect(model().attribute("curvePointList", equalTo(curvePoints)))
                    .andExpect(redirectedUrlTemplate("/curvePoint/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}

