package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.interfaces.ITradeService;
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
public class TradeController {
    
    @Autowired
    ITradeService tradeService;
    
    @RequestMapping("/trade/list")
    public String home(Model model) {
       
        model.addAttribute("trades", tradeService.getTrades());
        return "trade/list";
    }
    
    @GetMapping("/trade/add")
    public String addTrade(Trade bid) {
        return "trade/add";
    }
    
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
       
        if (!result.hasErrors()) {
            
            tradeService.createTrade(trade);
            model.addAttribute("trades", tradeService.getTrades());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }
    
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        
        model.addAttribute("trade", tradeService.getTrade(id));
        return "trade/update";
    }
    
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        
        String returnedVue=" ";
        if (result.hasErrors()) {
            returnedVue="redirect:/trade/update/{id}";
        } else {
            tradeService.updateTrade(trade);
            model.addAttribute("trades",tradeService.getTrades());
            returnedVue="redirect:/trade/list";
        }
        return returnedVue;
    }
    
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
       
        tradeService.deleteTrade(id);
        model.addAttribute("tradeList",tradeService.getTrades());
        return "redirect:/trade/list";
    }
}
