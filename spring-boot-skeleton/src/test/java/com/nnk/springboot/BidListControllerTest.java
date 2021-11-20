package com.nnk.springboot;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.interfaces.IBidListService;
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
@SpringBootTest(classes = BidListController.class)
public class BidListControllerTest {
    
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    IBidListService bidListService;
    
    
    List<BidList> bidLists = new ArrayList<>();
    BidList bidList1 = new BidList();
    BidList bidList2 = new BidList();
    
    
    @BeforeEach()
    public void config() {
        
        bidList1.setBidListId(1);
        bidList1.setAccount("account1");
        bidList1.setType("type1");
        bidList1.setBidQuantity(100.0);
        bidList2.setBidListId(2);
        bidList2.setAccount("account2");
        bidList2.setType("type2");
        bidList2.setBidQuantity(200.0);
        
        bidLists.add(bidList1);
        bidLists.add(bidList2);
    }
    
    
    @Test
    public void homeTest() {
        try {
            when(bidListService.getBidLists()).thenReturn(bidLists);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/bidList/list"))
                    .andExpect(model().attribute("bids", equalTo(bidLists)))
                    .andExpect(forwardedUrlTemplate("bidList/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void addBidFormTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrlTemplate("bidList/add"));
    }
    
    @Test
    public void validateTest() {
        try {
            BidList bidList3 = new BidList();
            bidList3.setBidListId(3);
            bidList3.setAccount("account3");
            bidList3.setType("type3");
            bidList3.setBidQuantity(300.0);
            bidLists.add(bidList3);
            when(bidListService.getBidLists()).thenReturn(bidLists);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/bidList/validate"))
                    .andExpect(model().attribute("bids", equalTo(bidLists)))
                    .andExpect(forwardedUrlTemplate("bidList/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void showUpdateFormTest() {
        try {
            when(bidListService.getBidList(1).get()).thenReturn((bidList1));
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/bidList/update/1"))
                    .andExpect(model().attribute("bidList", equalTo(bidList1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void updateBidTest() {
        try {
            
            bidLists.get(0).setBidQuantity(111.0);
            when(bidListService.getBidLists()).thenReturn(bidLists);
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/bidList/update/1"))
                    .andExpect(model().attribute("bids", equalTo(bidLists)))
                    .andExpect(redirectedUrlTemplate("/bidList/list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void deleteBidListTest() {
        try {
            bidLists.remove(1);
            when(bidListService.getBidLists()).thenReturn(bidLists);
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/bidList/delete/2"))
                    .andExpect(model().attribute("bids", equalTo(bidLists)))
                    .andExpect(redirectedUrlTemplate("/bidList/list"));
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
