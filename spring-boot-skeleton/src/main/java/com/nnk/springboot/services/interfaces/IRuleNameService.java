package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.RuleName;

import java.util.Optional;

public interface IRuleNameService {
    public Iterable<RuleName>getRuleNameList();
    public Optional<RuleName> getRuleName(Integer id);
    public void deleteRuleName(Integer id);
    public void updateRuleName(RuleName ruleName);
    public void createRuleName(RuleName ruleName);
}
