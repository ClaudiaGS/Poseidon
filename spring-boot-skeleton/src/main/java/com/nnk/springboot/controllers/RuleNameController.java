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
    // TODO: Inject RuleName service

    @Autowired
    IRuleNameService ruleNameService;
    
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        model.addAttribute("rules",ruleNameService.getRuleNameList());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        String returnedVue=" ";
        if(result.hasErrors()){
            returnedVue="/ruleName/list";
        }else{
            returnedVue="/ruleName/add";
            ruleNameService.updateRuleName(ruleName);
            model.addAttribute("rules", ruleNameService.getRuleNameList());
        }
        return returnedVue;
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        model.addAttribute("ruleName",ruleNameService.getRuleName(id));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        String returnedView=" ";
        if(result.hasErrors()){
            returnedView="ruleName/list";
        }else{
            ruleNameService.updateRuleName(ruleName);
            returnedView="redirect:/ruleName/list";
            model.addAttribute("rules", ruleNameService.getRuleNameList());
        }
        return returnedView;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        ruleNameService.deleteRuleName(id);
        model.addAttribute("rules",ruleNameService.getRuleNameList());
        return "redirect:/ruleName/list";
    }
}
