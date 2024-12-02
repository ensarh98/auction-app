package com.auctionapp.db.repository;

import com.auctionapp.db.model.BidRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing Bid instances.
 *
 * @author Ensar Horozović
 */

public interface BidRepository extends JpaRepository<BidRecord, Integer> {

    @Query("""
                select b.bidPrice from BidRecord as b where b.id = :id
            """)
    Double getBidPrice(@Param("id") Integer id);

    @Query("""
                select b.bids from BidRecord as b where b.id = :id
            """)
    Integer getBidBids(@Param("id") Integer id);

    @Query("""
                select count(*) from BidRecord as b where b.itemId = :itemId
            """)
    Integer countBidByItemId(@Param("itemId") Integer itemId);
}
