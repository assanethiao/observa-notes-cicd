
package com.observanotes.repository;

import com.observanotes.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByStudentId(Long studentId);

    Optional<Grade> findByIdAndStudentId(Long id, Long studentId);
}
