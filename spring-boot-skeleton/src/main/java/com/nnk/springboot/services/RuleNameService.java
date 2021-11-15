package com.nnk.springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.interfaces.IRuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuleNameService implements IRuleNameService {
    @Autowired
    RuleNameRepository ruleNameRepository;
    
    private static final Logger logger = LogManager.getLogger("RuleNameService");
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRuleNameService#getRuleNameList() 
     */
    @Override
    public Iterable<RuleName>getRuleNameList(){
        Iterable<RuleName>ruleNameIterable;
        ruleNameIterable=ruleNameRepository.findAll();
        logger.info("RuleName list is: "+asJsonString(ruleNameIterable));
        return ruleNameIterable;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRuleNameService#getRuleName(Integer) 
     */
    @Override
    public Optional<RuleName> getRuleName(Integer id){
        Optional<RuleName>ruleNameOptional;
        ruleNameOptional=ruleNameRepository.findById(id);
        logger.info("RuleName for id="+id+" is:"+asJsonString(ruleNameOptional.get()));
        return ruleNameOptional;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRuleNameService#deleteRuleName(Integer) 
     */
    @Override
    public void deleteRuleName(Integer id){
        logger.info("Deleting ruleName with id "+id);
        ruleNameRepository.deleteById(id);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRuleNameService#updateRuleName(RuleName) 
     */
    @Override
    public void updateRuleName(RuleName ruleName){
        logger.info("Updating ruleName "+ruleName);
        ruleNameRepository.save(ruleName);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRuleNameService#createRuleName(RuleName)
     */
    @Override
    public void createRuleName(RuleName ruleName){
        logger.info("Creating ruleName "+ruleName);
        ruleNameRepository.save(ruleName);
    }
    
    /**
     *
     * @param obj
     * @return String
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
