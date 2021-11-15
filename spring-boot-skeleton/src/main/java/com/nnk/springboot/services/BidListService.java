package com.nnk.springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.interfaces.IBidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidListService implements IBidListService {
    @Autowired
    BidListRepository bidListRepository;
    
    private static final Logger logger = LogManager.getLogger("BidListService");
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IBidListService#getBidLists()
     */
    @Override
    public Iterable<BidList>getBidLists(){
        Iterable<BidList>bidListIterable=bidListRepository.findAll();
        logger.info("BidList:"+asJsonString(bidListIterable));
        return bidListIterable;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IBidListService#getBidList(Integer) 
     */
    @Override
    public Optional<BidList> getBidList(Integer BidListId){
        Optional<BidList>bidListOptional=bidListRepository.findById(BidListId);
        logger.info("BidList for id "+BidListId+" is:"+asJsonString(bidListOptional.get()));
        return bidListOptional;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IBidListService#deleteBidList(Integer)
     */
    @Override
    public void deleteBidList(Integer BidListId){
        logger.info("Deleting bidList with id "+BidListId);
        bidListRepository.deleteById(BidListId);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IBidListService#updateBidList(BidList)
     */
    @Override
    public void updateBidList(BidList bidList){
        logger.info("Updating bidList "+bidList);
        bidListRepository.save(bidList);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IBidListService#createBidList(BidList)
     */
    @Override
    public void createBidList(BidList bidList){
        logger.info("Creating bidList "+bidList);
        bidListRepository.save(bidList);
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
