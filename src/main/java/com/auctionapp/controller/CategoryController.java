package com.auctionapp.controller;

import com.auctionapp.category.Category;
import com.auctionapp.category.CategoryDetail;
import com.auctionapp.category.CategoryService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Controller class for managing categories.
 * Handles requests for retrieving, creating, updating and deleting categories.
 *
 * @author Ensar Horozović
 */
@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping()
    public Integer createCategory(@RequestBody CategoryDetail category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Integer id, @RequestBody CategoryDetail category) {
        categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
