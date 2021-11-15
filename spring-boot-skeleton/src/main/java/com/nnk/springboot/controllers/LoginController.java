package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class LoginController {
    private static final Logger logger = LogManager.getLogger("LoginController");
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {

        return "login";
    }
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
