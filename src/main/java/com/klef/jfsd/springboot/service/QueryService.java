package com.klef.jfsd.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.jfsd.springboot.model.StudentQuery;
import com.klef.jfsd.springboot.repository.QueryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueryService {

    @Autowired
    private QueryRepository queryRepository;

    // Save a new query submitted by the student
    public void saveQuery(StudentQuery studentQuery) {
        queryRepository.save(studentQuery);
    }

    // Get all queries for teachers to review
    public List<StudentQuery> getAllQueries() {
        return queryRepository.findAll();
    }

    // Get queries specific to a student
    public List<StudentQuery> getQueriesByStudentEmail(String email) {
        return queryRepository.findByStudentEmail(email);
    }

    // Update a query with the teacher's solution
    public void updateSolution(Long queryId, String solution, LocalDateTime solutionAt) {
        StudentQuery query = queryRepository.findById(queryId)
                .orElseThrow(() -> new RuntimeException("Query not found with ID: " + queryId));
        query.setSolution(solution);
        query.setSolutionAt(solutionAt);
        queryRepository.save(query);
    }
    public StudentQuery findById(Long queryId) {
        return queryRepository.findById(queryId)
                .orElse(null);
    }

}
