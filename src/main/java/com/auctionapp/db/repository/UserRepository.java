package com.auctionapp.db.repository;

import com.auctionapp.db.model.UserRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing User instances.
 *
 * @author Ensar Horozović
 */
@Repository
public interface UserRepository extends JpaRepository<UserRecord, Integer> {

	UserRecord findOneByEmail(String email);

	@Query("""
            select count(u.id) from UserRecord as u where u.email = :email
            """)
	Integer isExistsUserByEmail(@Param("email") String email);


	Integer findUserRecordByPhotoId(Integer id);
}
