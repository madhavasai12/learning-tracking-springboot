package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByRecipientEmail(String recipientEmail); // Find announcements by specific student email

    // Fetch announcements for "all" students or when recipientEmail is null (global announcements)
    List<Announcement> findByRecipientEmailIsNullOrRecipientEmailEquals(String recipientEmail); 

    // Method to find announcements for 'all' students
    List<Announcement> findByRecipientEmailEquals(String recipientEmail);  // Explicitly for "all"
    @Query("SELECT a FROM Announcement a WHERE a.recipientEmail = 'all' OR a.recipientEmail = :email")
    List<Announcement> findAnnouncementsForAllAndSpecific(@Param("email") String email);


}
