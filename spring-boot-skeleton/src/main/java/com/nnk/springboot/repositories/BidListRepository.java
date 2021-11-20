package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * CRUD implementation for BidList object
 *
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
