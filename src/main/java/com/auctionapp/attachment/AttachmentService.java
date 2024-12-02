package com.auctionapp.attachment;

import com.auctionapp.db.model.AttachmentRecord;
import com.auctionapp.db.repository.AttachmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Integer createAttachment(MultipartFile file) throws IOException {
        AttachmentRecord pImage = new AttachmentRecord();
        pImage.setFilename(file.getName());
        pImage.setType(file.getContentType());
        pImage.setData(file.getBytes());
        return attachmentRepository.save(pImage).getId();
    }

    public Attachment getAttachment(Integer id) {
        var attachmentRecord = attachmentRepository.findAttachmentRecordById(id);
        return modelMapper.map(attachmentRecord, Attachment.class);
    }

}


