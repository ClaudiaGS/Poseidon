package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.interfaces.IBidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidListService implements IBidListService {
    @Autowired
    BidListRepository bidListRepository;
    
    @Override
    public Iterable<BidList>getBidLists(){
        return bidListRepository.findAll();
    }
    @Override
    public Optional<BidList> getBidList(Integer BidListId){
        return bidListRepository.findById(BidListId);
    }
    @Override
    public void deleteBidList(Integer BidListId){
        bidListRepository.deleteById(BidListId);
    }
    @Override
    public void updateBidList(BidList bidList){
        bidListRepository.save(bidList);
    }
    @Override
    public void createBidList(BidList bidList){
        bidListRepository.save(bidList);
    }
}
