package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.jfsd.springboot.model.StudentQuery;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<StudentQuery, Long> {
    List<StudentQuery> findByStudentEmail(String email);
    
}
