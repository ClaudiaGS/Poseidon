package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@PropertySource("classpath:application.properties")
@Controller
@RequestMapping("app")
public class LoginController {
    private static final Logger logger = LogManager.getLogger("LoginController");
    @Autowired
    private UserRepository userRepository;
    private static String authorizationRequestBaseUri = "http://localhost:8080/login/oauth/authorize";
    
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    
//    @GetMapping("/login")
//    public String login(){
//        return"login";
//}

//    @GetMapping("/login")
//    public String login(Model model, Environment environment, OAuth2AuthenticationToken authentication) {
//
//        String oauth2AuthenticationUrls= authorizationRequestBaseUri  + environment.getProperty("spring.security.oauth2.client.registration.github.client-id");
//      // oauth2AuthenticationUrls="https://github.com/login/oauth/authorize?response_type=code&client_id=ba649c59112c7a4a4c1b&scope=read:user&state=vSV36i6NjqizQ8l6JQv17-FAC0uYGfUg-ZS-NKJQfYs%3D&redirect_uri=http://localhost:8080/login/oauth2/code/github";
//        model.addAttribute("url", oauth2AuthenticationUrls);
//        return "login";
//    }
    
//
//    @GetMapping("/oauthlogin")
//    public String oauthlogin(Model model, Environment environment, OAuth2AuthenticationToken authentication) {
//
//        String oauth2AuthenticationUrls= authorizationRequestBaseUri  + environment.getProperty("spring.security.oauth2.client.registration.github.client-id");
//       oauth2AuthenticationUrls="https://github.com/login/oauth/authorize?response_type=code&client_id=ba649c59112c7a4a4c1b&scope=read:user&state=vSV36i6NjqizQ8l6JQv17-FAC0uYGfUg-ZS-NKJQfYs%3D&redirect_uri=http://localhost:8080/login/oauth2/code/github";
//        model.addAttribute("url", oauth2AuthenticationUrls);
//        return "oauthlogin";
//    }
//
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

//    @GetMapping("/access-denied")
//    public String error(String errorMessage, Model model) {
//         errorMessage= "You are not authorized for the requested data.";
//        model.addAttribute("error",errorMessage);
//        return "errorPage";
//    }
//@GetMapping("/access-denied")
//public String accessDeniedError(HttpServletRequest request, Model model) {
//
//    String errorMessage= "You are not authorized for the requested data.";
//    logger.info("current user is "+request.getRemoteUser());
//    model.addAttribute("errorMsg", errorMessage);
//    model.addAttribute("user",request.getRemoteUser());
//
//    return "/error/accessDenied";
//}


}
