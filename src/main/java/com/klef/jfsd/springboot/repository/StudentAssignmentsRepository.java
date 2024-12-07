package com.klef.jfsd.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.StudentAssignments;

@Repository
public interface StudentAssignmentsRepository extends JpaRepository<StudentAssignments, Long> {
    boolean existsByStudentEmailAndAssignmentName(String studentEmail, String assignmentName);
    List<StudentAssignments> findByStudentEmail(String email);
    Optional<StudentAssignments> findByStudentEmailAndAssignmentName(String email, String assignmentName);
    void deleteByAssignmentName(String assignmentName);
}
