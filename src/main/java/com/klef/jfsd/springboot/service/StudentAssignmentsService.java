package com.klef.jfsd.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.StudentAssignments;
import com.klef.jfsd.springboot.repository.StudentAssignmentsRepository;

@Service
public class StudentAssignmentsService {

    @Autowired
    private StudentAssignmentsRepository studentAssignmentsRepository;

    public boolean assignmentAlreadyExists(String studentEmail, String assignmentName) {
        return studentAssignmentsRepository.existsByStudentEmailAndAssignmentName(studentEmail, assignmentName);
    }

    public void saveAssignment(String studentName, String studentEmail, String assignmentName, MultipartFile file, LocalDateTime completedAt) {
        if (assignmentAlreadyExists(studentEmail, assignmentName)) {
            throw new RuntimeException("Assignment already submitted by this student.");
        }
        try {
            StudentAssignments studentAssignment = new StudentAssignments();
            studentAssignment.setStudentName(studentName);
            studentAssignment.setStudentEmail(studentEmail);
            studentAssignment.setAssignmentName(assignmentName);
            studentAssignment.setFileData(file.getBytes());
            studentAssignment.setCompletedAt(completedAt);

            studentAssignmentsRepository.save(studentAssignment);
        } catch (Exception e) {
            throw new RuntimeException("Error saving assignment", e);
        }
    }

    public List<StudentAssignments> getAssignments() {
        return studentAssignmentsRepository.findAll();
    }

    public List<StudentAssignments> getAssignmentsByStudentEmail(String email) {
        return studentAssignmentsRepository.findByStudentEmail(email);
    }

    public void updateGradeByEmailAndAssignmentName(String email, String assignmentName, String grade) {
        StudentAssignments assignment = studentAssignmentsRepository.findByStudentEmailAndAssignmentName(email, assignmentName)
            .orElseThrow(() -> new RuntimeException("Assignment not found for the provided email and assignment name."));
        assignment.setGrade(grade);
        studentAssignmentsRepository.save(assignment);
    }
    
    public void deleteStudentSubmissionsByAssignmentName(String assignmentName) {
        List<StudentAssignments> submissions = studentAssignmentsRepository.findAll()
            .stream()
            .filter(submission -> submission.getAssignmentName().equals(assignmentName))
            .toList();

        studentAssignmentsRepository.deleteAll(submissions);
    }

}
