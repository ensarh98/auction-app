package com.auctionapp.db.repository;

import com.auctionapp.db.model.AttachmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Attachment instances.
 *
 * @author Ensar Horozović
 */
@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentRecord, Integer> {

    AttachmentRecord findAttachmentRecordById(Integer id);
}
