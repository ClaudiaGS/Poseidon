package com.nnk.springboot;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITradeService;
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
@SpringBootTest(classes = TradeController.class)
public class TradeControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    ITradeService tradeService;
    
    
    List<Trade> trades = new ArrayList<>();
    Trade trade1=new Trade();
    Trade trade2=new Trade();
    
    @BeforeEach()
    public void config() {

        trade1.setTradeId(1);
        trade1.setAccount("account1");
        trade1.setType("type1");
        trade1.setBuyQuantity(1000.0);
        trade2.setTradeId(2);
        trade2.setAccount("account2");
        trade2.setType("type2");
        trade2.setBuyQuantity(2000.0);
        trades.add(trade1);
        trades.add(trade2);
    }
    
    
    @Test
    public void homeTest() {
        try {
            when(tradeService.getTrades()).thenReturn(trades);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/trade/list"))
                    .andExpect(model().attribute("trades", equalTo(trades)))
                    .andExpect(forwardedUrlTemplate("trade/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addTradeForm() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrlTemplate("trade/add"));
    }
    
    @Test
    public void validateTest() {
        try {
            Trade trade3=new Trade();
            trade3.setTradeId(3);
            trade3.setAccount("account3");
            trade3.setType("type3");
            trade3.setBuyQuantity(3000.0);
            
            trades.add(trade3);
            
            when(tradeService.getTrades()).thenReturn(trades);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/trade/validate"))
                    .andExpect(model().attribute("trades", equalTo(trades)))
                    .andExpect(redirectedUrl("/trade/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void showUpdateFormTest() {
        try {
            when(tradeService.getTrade(1).get()).thenReturn((trade1));
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/trade/update/1"))
                    .andExpect(model().attribute("trade", equalTo(trade1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void updateTradeTest() {
        try {
            
            trades.get(0).setBuyQuantity(1111.0);
            when(tradeService.getTrades()).thenReturn(trades);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/trade/update/1"))
                    .andExpect(model().attribute("trades", equalTo(trades)))
                    .andExpect(redirectedUrlTemplate("/trade/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void deleteTradeTest() {
        try {
            trades.remove(1);
            when(tradeService.getTrades()).thenReturn(trades);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/trade/delete/2"))
                    .andExpect(model().attribute("tradeList", equalTo(trades)))
                    .andExpect(redirectedUrlTemplate("/trade/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}

