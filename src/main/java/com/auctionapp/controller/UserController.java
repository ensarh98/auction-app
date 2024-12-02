package com.auctionapp.controller;

import com.auctionapp.common.UploadFileResponse;
import com.auctionapp.db.model.UserRecord;
import com.auctionapp.db.repository.UserRepository;
import com.auctionapp.user.User;
import com.auctionapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public UserRecord getUser(@PathVariable Integer id) throws IOException {
        var user = userRepository.findById(id).orElseThrow();
        System.out.println(user);
        return user;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/{id}/photo")
    public UploadFileResponse uploadImage(@PathVariable Integer id, @RequestPart(value = "file") MultipartFile file) throws IOException {
        userService.uploadProfilePhoto(id, file);
        return new UploadFileResponse(id, UploadFileResponse.ResponseStatus.UPLOAD_SUCCESSFUL);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable Integer id) {
        var photo = userService.getUserProfilePhoto(id);
        return buildDownloadResponse(photo);
    }
}
