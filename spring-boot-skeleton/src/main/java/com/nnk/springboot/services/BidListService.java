package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidListService {
    @Autowired
    BidListRepository bidListRepository;
    
    public Iterable<BidList>getBidLists(){
        return bidListRepository.findAll();
    }
    public Optional<BidList> getBidList(Integer BidListId){
        return bidListRepository.findById(BidListId);
    }
    public void deleteBidList(Integer BidListId){
        bidListRepository.deleteById(BidListId);
    }
    public void updateBidList(BidList bidList){
        Optional<BidList> existingBidList=bidListRepository.findById(bidList.getBidListId());
   //???
    }
    public void createBidList(BidList bidList){
        bidListRepository.save(bidList);
    }
}
