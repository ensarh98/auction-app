package com.auctionapp.item;

import com.auctionapp.attachment.Attachment;
import com.auctionapp.attachment.AttachmentService;
import com.auctionapp.common.AppException;
import com.auctionapp.common.UploadFileResponse;
import com.auctionapp.db.model.ItemRecord;
import com.auctionapp.db.repository.BidRepository;
import com.auctionapp.db.repository.ItemRepository;
import com.auctionapp.db.repository.SubcategoryRepository;
import com.auctionapp.db.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ItemService class for managing item information.
 * Provides methods for creating, updating, deleting and retrieving user
 * information.
 * Uses ItemRepository for data access.
 *
 * @author Ensar Horozović
 */
@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private MessageSource messageSource;

    public List<Item> getItems() {
        var itemList = itemRepository.findAll();

        return itemList.stream()
                .map(item -> modelMapper.map(item, Item.class))
                .collect(Collectors.toList());
    }

    private ItemRecord getPopulatedItemRecord(Item item) {
        var itemRecord = new ItemRecord();
        itemRecord.setName(item.getName());
        itemRecord.setDescription(item.getDescription());
        itemRecord.setAddress(item.getAddress());
        itemRecord.setPhotoId(item.getPhotoId());
        itemRecord.setStartPrice(item.getStartPrice());
        itemRecord.setStartDate(item.getStartDate());
        itemRecord.setEndDate(item.getEndDate());
        itemRecord.setSubcategoryId(item.getSubcategoryId());
        itemRecord.setUserId(item.getUserId());

        return itemRecord;
    }

    private Boolean checkItemStartDate(Date itemStartDate, Date itemEndDate) {
        var today = new Date();
        return today.compareTo(itemStartDate) > 0 || itemStartDate.compareTo(itemEndDate) > 0;
    }

    public Integer createItem(Item item) {
        if (item == null || item.getName() == null || item.getStartPrice() == null || item.getStartDate() == null
                || item.getEndDate() == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        if (item.getStartPrice() < 0) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("NEGATIVE_ITEM_PRICE", null, LocaleContextHolder.getLocale()));
        }

        var userRecord = userRepository.findById(item.getUserId());
        if (!userRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("WRONG_USER_ID", null, LocaleContextHolder.getLocale()));
        }
        var subcategoryRecord = subcategoryRepository.findById(item.getSubcategoryId());
        if (!subcategoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("WRONG_SUBCATEGORY_ID", null, LocaleContextHolder.getLocale()));
        }

         // Checking whether the start date of the sale is before the end date and the current one
        if (checkItemStartDate(item.getStartDate(), item.getEndDate())) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("DATE_NOT_VALID", null, LocaleContextHolder.getLocale()));
        }

        var itemRecord = getPopulatedItemRecord(item);
        var itemId = itemRepository.save(itemRecord).getId();
        return itemId;
    }

    public void updateItem(Integer itemId, Item item) {
        if (item == null || item.getName() == null || item.getStartPrice() == null || item.getStartDate() == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        if (item.getStartPrice() < 0) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("NEGATIVE_ITEM_PRICE", null, LocaleContextHolder.getLocale()));
        }

        var userRecord = userRepository.findById(item.getUserId());
        if (!userRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("WRONG_USER_ID", null, LocaleContextHolder.getLocale()));
        }

        var subcategoryRecord = subcategoryRepository.findById(item.getSubcategoryId());
        if (!subcategoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("WRONG_SUBCATEGORY_ID", null, LocaleContextHolder.getLocale()));
        }

        // Checking whether the start date of the sale is before the end date and the current one
        if (checkItemStartDate(item.getStartDate(), item.getEndDate())) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("DATE_NOT_VALID", null, LocaleContextHolder.getLocale()));
        }

        var itemRecord = itemRepository.findById(itemId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR,
                        messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale())));
        itemRecord.setName(item.getName());
        itemRecord.setDescription(item.getDescription());
        itemRecord.setAddress(item.getAddress());
        itemRecord.setPhotoId(item.getPhotoId());
        itemRecord.setStartPrice(item.getStartPrice());
        itemRecord.setStartDate(item.getStartDate());
        itemRecord.setEndDate(item.getEndDate());
        itemRecord.setSubcategoryId(item.getSubcategoryId());
        itemRecord.setUserId(item.getUserId());

        itemRepository.save(itemRecord);
    }

    public void deleteItem(Integer itemId) {
        if (itemId == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var itemRecord = itemRepository.findById(itemId);
        if (!itemRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale()));
        }

        var bidCounter = bidRepository.countBidByItemId(itemId);
        if (bidCounter > 0) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("BID_EXIST", null, LocaleContextHolder.getLocale()));
        }

        itemRepository.deleteById(itemId);
    }

    public UploadFileResponse uploadPhotoItem(Integer itemId, MultipartFile file) throws IOException {
        if (itemId == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var item = itemRepository.findById(itemId).orElseThrow();
        var attachmentId = attachmentService.createAttachment(file);
        item.setPhotoId(attachmentId);

        itemRepository.save(item);

        return new UploadFileResponse(attachmentId, UploadFileResponse.ResponseStatus.UPLOAD_SUCCESSFUL);
    }

    public Attachment getItemPhoto(Integer itemId) {
        if (itemId == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }
        var item = itemRepository.findById(itemId).orElseThrow();
        if (item.getPhotoId() != null) {
            return attachmentService.getAttachment(item.getPhotoId());
        }
        return null;

    }

}
