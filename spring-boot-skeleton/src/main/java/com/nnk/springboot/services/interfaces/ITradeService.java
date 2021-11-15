package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Trade;

import java.util.Optional;

public interface ITradeService {
    
    /**
     *
     * @return Iterable<Trade>
     */
    public Iterable<Trade>getTrades();
    
    /**
     *
     * @param tradeId
     * @return Optional<Trade>
     */
    public Optional<Trade> getTrade(Integer tradeId);
    
    /**
     *
     * @param tradeId
     * @return void
     */
    public void deleteTrade(Integer tradeId);
    
    /**
     *
     * @param trade
     * @return void
     */
    public void updateTrade(Trade trade);
    
    /**
     *
     * @param trade
     * @return void
     */
    public void createTrade(Trade trade);
}
