package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.klef.jfsd.springboot.model.StudentQuery;
import com.klef.jfsd.springboot.service.QueryService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/queries")
@CrossOrigin
public class QueryController {

    @Autowired
    private QueryService queryService;

    // Endpoint for students to submit queries
    @PostMapping("/submit")
    public void submitQuery(@RequestBody StudentQuery studentQuery) {
        studentQuery.setAskedAt(LocalDateTime.now());
        queryService.saveQuery(studentQuery);
    }

    // Endpoint to retrieve all queries for teachers
    @GetMapping("/all")
    public List<StudentQuery> getAllQueries() {
        return queryService.getAllQueries();
    }

    // Endpoint to retrieve queries for a specific student
    @GetMapping("/student")
    public List<StudentQuery> getQueriesByStudentEmail(@RequestParam String email) {
        return queryService.getQueriesByStudentEmail(email);
    }

    // Endpoint for teachers to provide solutions
    @PostMapping("/solution")
    public ResponseEntity<String> submitSolution(@RequestParam Long queryId, @RequestParam String solution) {
        StudentQuery query = queryService.findById(queryId);
        if (query == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query not found.");
        }
        if (query.getSolution() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Solution already submitted.");
        }
        queryService.updateSolution(queryId, solution, LocalDateTime.now());
        return ResponseEntity.ok("Solution submitted successfully.");
    }

}
