package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.model.Announcement;
import com.klef.jfsd.springboot.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    // Fetch all announcements for 'all' and specific student
    public List<Announcement> getAnnouncementsForStudent(String studentEmail) {
        return announcementRepository.findAnnouncementsForAllAndSpecific(studentEmail);
    }



    // Fetch announcements for a specific student
    

    // Create a new announcement
    public Announcement createAnnouncement(String announcementText, String createdBy, String recipientEmail) {
        Announcement announcement = new Announcement(announcementText, createdBy, recipientEmail, LocalDateTime.now());
        return announcementRepository.save(announcement);
    }
    

}
