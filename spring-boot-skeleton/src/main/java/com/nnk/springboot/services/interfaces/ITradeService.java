package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Trade;

import java.util.Optional;

public interface ITradeService {
    public Iterable<Trade>getTrades();
    public Optional<Trade> getTrade(Integer tradeId);
    public void deleteTrade(Integer tradeId);
    public void updateTrade(Trade trade);
    public void createTrade(Trade trade);
}
