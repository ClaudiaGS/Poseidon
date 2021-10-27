package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.BidList;

import java.util.Optional;

public interface IBidListService {
    public Iterable<BidList>getBidLists();
    public Optional<BidList> getBidList(Integer BidListId);
    public void deleteBidList(Integer BidListId);
    public void updateBidList(BidList bidList);
    public void createBidList(BidList bidList);
}
