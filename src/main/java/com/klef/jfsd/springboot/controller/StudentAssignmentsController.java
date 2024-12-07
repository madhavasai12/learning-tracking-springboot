package com.klef.jfsd.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.StudentAssignments;
import com.klef.jfsd.springboot.service.StudentAssignmentsService;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin
public class StudentAssignmentsController {
	

    private static final Logger logger = LoggerFactory.getLogger(StudentAssignmentsController.class);

    @Autowired
    private StudentAssignmentsService studentAssignmentsService;

    @PostMapping("/mark-done")
    public void markAssignmentAsDone(
        @RequestParam String studentName,
        @RequestParam String studentEmail,
        @RequestParam String assignmentName,
        @RequestParam("file") MultipartFile file) {
        studentAssignmentsService.saveAssignment(studentName, studentEmail, assignmentName, file, LocalDateTime.now());
    }

    @GetMapping
    public List<StudentAssignments> getAssignments() {
        return studentAssignmentsService.getAssignments();
    }

    @GetMapping("/submissions")
    public List<StudentAssignments> getStudentSubmissions(@RequestParam String email) {
        return studentAssignmentsService.getAssignmentsByStudentEmail(email);
    }

    @PostMapping("/grade")
    public void submitGrade(@RequestParam String studentEmail, @RequestParam String assignmentName, @RequestParam String grade) {
        studentAssignmentsService.updateGradeByEmailAndAssignmentName(studentEmail, assignmentName, grade);
    }
}
