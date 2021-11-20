package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.interfaces.IRuleNameService;
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
public class RuleNameController {
    
    @Autowired
    IRuleNameService ruleNameService;
    
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        
        model.addAttribute("rules", ruleNameService.getRuleNameList());
        return "ruleName/list";
    }
    
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName, Model model) {
        model.addAttribute("ruleName", ruleName);
        return "ruleName/add";
    }
    
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.updateRuleName(ruleName);
        model.addAttribute("rules", ruleNameService.getRuleNameList());
        return "redirect:/ruleName/list";
    }
    
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        
        model.addAttribute("ruleName", ruleNameService.getRuleName(id));
        return "ruleName/update";
    }
    
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
               
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameService.updateRuleName(ruleName);
        model.addAttribute("rules", ruleNameService.getRuleNameList());
        return "redirect:/ruleName/list";
    }
    
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
       
        ruleNameService.deleteRuleName(id);
        model.addAttribute("rules", ruleNameService.getRuleNameList());
        return "redirect:/ruleName/list";
    }
}
