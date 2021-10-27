package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.interfaces.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService implements ITradeService {
    @Autowired
    TradeRepository tradeRepository;
    
    @Override
    public Iterable<Trade>getTrades(){
        return tradeRepository.findAll();
    }
    @Override
    public Optional<Trade> getTrade(Integer tradeId){
        return tradeRepository.findById(tradeId);
    }
    @Override
    public void deleteTrade(Integer tradeId){
        tradeRepository.deleteById(tradeId);
    }
    @Override
    public void updateTrade(Trade trade){
         tradeRepository.save(trade);
    }
    @Override
    public void createTrade(Trade trade){
       
  
        tradeRepository.saveAndFlush(trade);
    }
    
}
