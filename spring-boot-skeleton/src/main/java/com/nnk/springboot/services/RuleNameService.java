package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.interfaces.IRuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuleNameService implements IRuleNameService {
    @Autowired
    RuleNameRepository ruleNameRepository;
    
    @Override
    public Iterable<RuleName>getRuleNameList(){
        return ruleNameRepository.findAll();
    }
    @Override
    public Optional<RuleName> getRuleName(Integer id){
        return ruleNameRepository.findById(id);
    }
    @Override
    public void deleteRuleName(Integer id){
        ruleNameRepository.deleteById(id);
    }
    @Override
    public void updateRuleName(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }
    @Override
    public void createRuleName(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }
}
