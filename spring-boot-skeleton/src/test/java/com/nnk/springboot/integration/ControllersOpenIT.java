package com.nnk.springboot.integration;

import com.nnk.springboot.controllers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ControllersOpenIT {
    
    @Autowired
    private BidListController bidListController;
    @Autowired
    private CurveController curveController;
    @Autowired
    private HomeController homeController;
    @Autowired
    private RatingController ratingController;
    @Autowired
    private RuleNameController ruleNameController;
    @Autowired
    private TradeController tradeController;
    @Autowired
    private UserController userController;
    @Autowired
    private AccessDeniedController accessDeniedController;
    
    @Test
    public void contextLoads() throws Exception{
        assertThat(bidListController).isNotNull();
        assertThat(curveController).isNotNull();
        assertThat(homeController).isNotNull();
        assertThat(ratingController).isNotNull();
        assertThat(ruleNameController).isNotNull();
        assertThat(tradeController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(accessDeniedController).isNotNull();
    }
}


//        @Autowired
//        private TestRestTemplate template;
//
//        // ... other methods
//
//        @Test
//        public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
//            ResponseEntity<String> result = template.withBasicAuth("user1", "password1")
//                    .getForEntity("/bidList/list", String.class);
//            assertEquals(HttpStatus.OK, result.getStatusCode());
//        }
//    }