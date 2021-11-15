package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.RuleName;

import java.util.Optional;

public interface IRuleNameService {
    
    /**
     *
     * @return Iterable<RulaName>
     */
    public Iterable<RuleName>getRuleNameList();
    
    /**
     *
     * @param id
     * @return Optional<RuleName>
     */
    public Optional<RuleName> getRuleName(Integer id);
    
    /**
     *
     * @param id
     * @return void
     */
    public void deleteRuleName(Integer id);
    
    /**
     *
     * @param ruleName
     * @return void
     */
    public void updateRuleName(RuleName ruleName);
    
    /**
     *
     * @param ruleName
     * @return void
     */
    public void createRuleName(RuleName ruleName);
}
