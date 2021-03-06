package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.interfaces.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {
    
    @Autowired
    IRatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        
        model.addAttribute("ratings", ratingService.getRatingList());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating, Model model) {
        model.addAttribute("rating", rating);
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        
        if(result.hasErrors()){
            return "rating/add";
        }
        ratingService.createRating(rating);
        model.addAttribute("ratings", ratingService.getRatingList());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
       
        model.addAttribute("rating", ratingService.getRating(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
       
        if(result.hasErrors()){
            return "rating/update";
        }
        ratingService.updateRating(rating);
        model.addAttribute("ratings",ratingService.getRatingList());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
       
        ratingService.deleteRating(id);
        model.addAttribute("ratings", ratingService.getRatingList());
        return "redirect:/rating/list";
    }
}
