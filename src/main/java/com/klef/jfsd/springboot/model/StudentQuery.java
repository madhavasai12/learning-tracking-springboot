package com.klef.jfsd.springboot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_queries")
public class StudentQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String studentEmail;
    private String query;

    @Column(name = "asked_at")
    private LocalDateTime askedAt;

    private String solution;

    @Column(name = "solution_at")
    private LocalDateTime solutionAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public LocalDateTime getAskedAt() {
		return askedAt;
	}

	public void setAskedAt(LocalDateTime askedAt) {
		this.askedAt = askedAt;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public LocalDateTime getSolutionAt() {
		return solutionAt;
	}

	public void setSolutionAt(LocalDateTime solutionAt) {
		this.solutionAt = solutionAt;
	}
    
    

    // Getters and Setters
    // ... (unchanged from your provided code)
}
