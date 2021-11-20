package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.interfaces.IBidListService;
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
public class BidListController {
    
    @Autowired
    IBidListService bidListService;
    
    @RequestMapping("/bidList/list")
    public String home(Model model) {
       
        model.addAttribute("bids",bidListService.getBidLists());
        return "bidList/list";
    }
    
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList, Model model) {
        model.addAttribute("bidList", bidList);
        return "bidList/add";
    }
    
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        
        if (!result.hasErrors()) {
            bidListService.createBidList(bidList);
            model.addAttribute("bids", bidListService.getBidLists());
            return "bidList/list";
        }
        return "bidList/add";
    }
    
  
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
       
        model.addAttribute("bidList",bidListService.getBidList(id));
        
        return "bidList/update";
    }
    
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
      
        if(result.hasErrors()){
            model.addAttribute("bidList",bidList);
            return "bidList/update";
        }
            bidListService.updateBidList(bidList);
            model.addAttribute("bids", bidListService.getBidLists());
        
        return "redirect:/bidList/list";
    }
    
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        
        bidListService.deleteBidList(id);
        model.addAttribute("bids",bidListService.getBidLists());
        return "redirect:/bidList/list";
    }
}
