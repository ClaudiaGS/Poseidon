package com.nnk.springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.interfaces.ITradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService implements ITradeService {
    @Autowired
    TradeRepository tradeRepository;
    
    private static final Logger logger = LogManager.getLogger("TradeService");
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ITradeService#getTrades()
     */
    @Override
    public Iterable<Trade> getTrades() {
        Iterable<Trade> tradeIterable = tradeRepository.findAll();
        logger.info("Trade list:" + asJsonString(tradeIterable));
        return tradeIterable;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ITradeService#getTrade(Integer)
     */
    @Override
    public Optional<Trade> getTrade(Integer tradeId) {
        Optional<Trade> tradeOptional = tradeRepository.findById(tradeId);
        logger.info("Trade for id=" + tradeId + " is:" + asJsonString(tradeOptional.get()));
        return tradeOptional;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ITradeService#deleteTrade(Integer)
     */
    @Override
    public void deleteTrade(Integer tradeId) {
        logger.info("Deleting trade with id=" + tradeId);
        tradeRepository.deleteById(tradeId);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ITradeService#updateTrade(Trade)
     */
    @Override
    public void updateTrade(Trade trade) {
        logger.info("Updating trade " + trade);
        tradeRepository.save(trade);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ITradeService#createTrade(Trade)
     */
    @Override
    public void createTrade(Trade trade) {
        logger.info("Creating trade "+trade);
        tradeRepository.saveAndFlush(trade);
    }
    
    /**
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
