package com.auctionapp.controller;

import com.auctionapp.common.UploadFileResponse;
import com.auctionapp.item.Item;
import com.auctionapp.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for managing items.
 * Handles requests for retrieving, creating, updating and deleting items.
 *
 * @author Ensar Horozović
 */
@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @GetMapping()
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @PostMapping()
    public Integer createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Integer id, @RequestBody Item item) {
        itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/{id}/photo")
    public UploadFileResponse uploadImage(@PathVariable Integer id, @RequestPart(value = "file") MultipartFile file)
            throws IOException {
        return itemService.uploadPhotoItem(id, file);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable Integer id) {
        var attachment = itemService.getItemPhoto(id);
        return buildDownloadResponse(attachment);
    }
}
