package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.interfaces.ICurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service
    @Autowired
    ICurvePointService curvePointService;
    
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        // TODO: find all Curve Point, add to model
        model.addAttribute("curvePointList",curvePointService.getCurvePointList());
        return "curvePoint/list";
    }
    
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint, Model model) {
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }
    
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        String returnedVue=" ";
        if(result.hasErrors()){
            returnedVue="curvePoint/add";
        }else{
            curvePointService.createCurvePoint(curvePoint);
            model.addAttribute("curvePointList", curvePointService.getCurvePointList());
            returnedVue="redirect:/curvePoint/list";
        }
        return returnedVue;
    }
    
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
       model.addAttribute("curvePoint",curvePointService.getCurvePoint(id));
        return "curvePoint/update";
    }
    
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
       
        if(result.hasErrors()){
            return "curvePoint/update";
        }
            curvePointService.updateCurvePoint(curvePoint);
            model.addAttribute("curvePointList",curvePointService.getCurvePointList());
            return "redirect:/curvePoint/list";
        
    }
    
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        curvePointService.deleteCurvePoint(id);
        model.addAttribute("curvePointList",curvePointService.getCurvePointList());
        return "redirect:/curvePoint/list";
    }
}
