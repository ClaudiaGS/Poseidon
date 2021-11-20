package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.BidList;

import java.util.Optional;

public interface IBidListService {
    
    /**
     *
     * @return Iterable<BidList>
     */
    public Iterable<BidList>getBidLists();
    
    /**
     *
     * @param BidListId
     * @return Optional<BidList>
     */
    public Optional<BidList> getBidList(Integer BidListId);
    
    /**
     *
     * @param BidListId
     * @return void
     */
    public void deleteBidList(Integer BidListId);
    
    /**
     *
     * @param bidList
     * @return void
     */
    public void updateBidList(BidList bidList);
    
    /**
     *
     * @param bidList
     * @return void
     */
    public void createBidList(BidList bidList);
}
