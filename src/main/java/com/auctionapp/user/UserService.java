package com.auctionapp.user;

import com.auctionapp.attachment.Attachment;
import com.auctionapp.attachment.AttachmentService;
import com.auctionapp.common.AppException;
import com.auctionapp.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private MessageSource messageSource;

    public void uploadProfilePhoto(Integer userId, MultipartFile file) throws IOException {
        if (userId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var user = userRepository.findById(userId).orElseThrow();
        var attachmentId = attachmentService.createAttachment(file);
        user.setPhotoId(attachmentId);
        userRepository.save(user);
    }

    public Attachment getUserProfilePhoto(Integer userId) {
        if (userId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }
        var user = userRepository.findById(userId).orElseThrow();
        if (user.getPhotoId() != null) {
            return attachmentService.getAttachment(user.getPhotoId());
        }
        return null;
    }
}
