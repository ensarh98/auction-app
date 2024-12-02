package com.auctionapp.subcategory;

import com.auctionapp.category.CategoryDetail;
import com.auctionapp.common.AppException;
import com.auctionapp.db.model.SubcategoryRecord;
import com.auctionapp.db.repository.CategoryRepository;
import com.auctionapp.db.repository.SubcategoryRepository;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SubcategoryService class for managing subcategory information.
 * Provides methods for creating, updating, deleting and retrieving user information.
 * Uses SubcategoryRepository for data access.
 *
 * @author Ensar Horozović
 */

@Transactional
@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public List<Subcategory> getSubcategories() {
        var subcategoryList = subcategoryRepository.findAll();

        return subcategoryList.stream()
                .map(subcategory -> modelMapper.map(subcategory, Subcategory.class))
                .collect(Collectors.toList());
    }

    public Integer createSubcategory(SubcategoryDetail subcategory) {
        if (subcategory == null || subcategory.getName() == null || subcategory.getCategoryId() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }
        var categoryRecord = categoryRepository.findById(subcategory.getCategoryId());
        if(!categoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("WRONG_CATEGORY_ID", null, LocaleContextHolder.getLocale()));
        }

        var subcategoryRecord = new SubcategoryRecord();
        subcategoryRecord.setName(subcategory.getName());
        subcategoryRecord.setCategoryId(subcategory.getCategoryId());

        var subcategoryId = subcategoryRepository.save(subcategoryRecord).getId();
        return subcategoryId;
    }

    public void updateSubcategory(Integer subcategoryId, SubcategoryDetail subcategory) {
        if (subcategory == null || subcategory.getName() == null || subcategory.getCategoryId() == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }
        var categoryRecord = categoryRepository.findById(subcategory.getCategoryId());
        if(!categoryRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("WRONG_CATEGORY_ID", null, LocaleContextHolder.getLocale()));
        }

        var subcategoryRecord = subcategoryRepository.findById(subcategoryId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale())));
        subcategoryRecord.setName(subcategory.getName());
        subcategoryRecord.setCategoryId(subcategory.getCategoryId());

        subcategoryRepository.save(subcategoryRecord);
    }

    public void deleteSubcategory(Integer subcategoryId) {
        if (subcategoryId == null) {
            throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        subcategoryRepository.findById(subcategoryId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR, messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale())));

        subcategoryRepository.deleteById(subcategoryId);
    }
}
