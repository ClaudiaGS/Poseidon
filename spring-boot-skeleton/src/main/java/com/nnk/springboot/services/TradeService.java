package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    TradeRepository tradeRepository;
    
    public Iterable<Trade>getTrades(){
        return tradeRepository.findAll();
    }
    public Optional<Trade> getTrade(Integer tradeId){
        return tradeRepository.findById(tradeId);
    }
    public void deleteTrade(Integer tradeId){
        tradeRepository.deleteById(tradeId);
    }
    public void updateTrade(Trade trade){
        Optional<Trade> existingTrade=tradeRepository.findById(trade.getTradeId());
        existingTrade.equals(trade);
    }
    public void createTrade(Trade trade){
        tradeRepository.save(trade);
    }
    
}
