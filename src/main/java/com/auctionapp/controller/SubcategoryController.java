package com.auctionapp.controller;

import com.auctionapp.subcategory.Subcategory;
import com.auctionapp.subcategory.SubcategoryDetail;
import com.auctionapp.subcategory.SubcategoryService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Controller class for managing subcategories.
 * Handles requests for retrieving, creating, updating and deleting subcategories.
 *
 * @author Ensar Horozović
 */
@RestController
@CrossOrigin
@RequestMapping("/api/subcategories")
public class SubcategoryController extends BaseController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping()
    public List<Subcategory> getSubcategories() {
        return subcategoryService.getSubcategories();
    }

    @PostMapping()
    public Integer createSubcategory(@RequestBody SubcategoryDetail subcategory) {
        return subcategoryService.createSubcategory(subcategory);
    }

    @PutMapping("/{id}")
    public void updateSubcategory(@PathVariable Integer id, @RequestBody SubcategoryDetail subcategory) {
        subcategoryService.updateSubcategory(id, subcategory);
    }

    @DeleteMapping("/{id}")
    public void deleteSubcategory(@PathVariable Integer id) {
        subcategoryService.deleteSubcategory(id);
    }
}
