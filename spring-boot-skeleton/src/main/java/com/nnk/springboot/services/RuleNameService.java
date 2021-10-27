package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuleNameService {
    @Autowired
    RuleNameRepository ruleNameRepository;
    
    public Iterable<RuleName>getRuleNameList(){
        return ruleNameRepository.findAll();
    }
    public Optional<RuleName> getRuleName(Integer id){
        return ruleNameRepository.findById(id);
    }
    public void deleteRuleName(Integer id){
        ruleNameRepository.deleteById(id);
    }
    public void updateRuleName(RuleName ruleName){
        Optional<RuleName> existingRuleName=ruleNameRepository.findById(ruleName.getId());
        existingRuleName.equals(ruleName);
    }
    public void createRuleName(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }
}
