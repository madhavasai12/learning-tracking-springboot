package com.klef.jfsd.springboot.controller;

import com.klef.jfsd.springboot.model.Announcement;
import com.klef.jfsd.springboot.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    

    @GetMapping("/student/{email}")
    public List<Announcement> getAnnouncementsForStudent(@PathVariable String email) {
        return announcementService.getAnnouncementsForStudent(email);
    }


    @PostMapping
    public Announcement createAnnouncement(@RequestParam String announcementText,
                                           @RequestParam String createdBy,
                                           @RequestParam String recipientEmail) {
        return announcementService.createAnnouncement(announcementText, createdBy, recipientEmail);
    }
}
