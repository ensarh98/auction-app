package com.auctionapp.controller;

import com.auctionapp.bid.Bid;
import com.auctionapp.bid.BidService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Controller class for managing bids.
 * Handles requests for retrieving, creating, updating and deleting bids.
 *
 * @author Ensar Horozović
 */
@RestController
@CrossOrigin
@RequestMapping("/api/bids")
public class BidController extends BaseController {

    @Autowired
    private BidService bidService;

    @GetMapping()
    public List<Bid> getBids() {
        return bidService.getBids();
    }

    @PostMapping()
    public Integer createBid(@RequestBody Bid bid) {
        return bidService.createBid(bid);
    }

    @PutMapping("/{id}")
    public void updateBid(@PathVariable Integer id, @RequestBody Bid bid) {
        bidService.updateBid(id, bid);
    }

    @DeleteMapping("/{id}")
    public void deleteBid(@PathVariable Integer id) {
        bidService.deleteBid(id);
    }
}
