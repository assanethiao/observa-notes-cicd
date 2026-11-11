
package com.observanotes.service;

import com.observanotes.dto.GradeRequest;
import com.observanotes.dto.GradeResponse;
import com.observanotes.model.Grade;
import com.observanotes.model.Student;
import com.observanotes.repository.GradeRepository;
import com.observanotes.repository.StudentRepository;
import com.observanotes.dto.GradeAverageResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public GradeService(
            GradeRepository gradeRepository,
            StudentRepository studentRepository) {

        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    // Conversion d'une entité en réponse DTO
    private GradeResponse toResponse(Grade grade) {

        return new GradeResponse(
            grade.getId(),
            grade.getSubject(),
            grade.getValue(),
            grade.getStudent().getId()
        );
    }

    // Ajouter une note à un étudiant
    public GradeResponse createGrade(
            Long studentId,
            GradeRequest request) {

        Student student = studentRepository.findById(studentId)
            .orElse(null);

        if (student == null) {
            return null;
        }

        Grade grade = new Grade();

        grade.setSubject(request.subject());
        grade.setValue(request.value());

        student.addGrade(grade);

        Grade savedGrade = gradeRepository.save(grade);

        return toResponse(savedGrade);
    }

    // Récupérer toutes les notes d'un étudiant
    @Transactional(readOnly = true)
    public List<GradeResponse> getGradesByStudent(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            return null;
        }

        return gradeRepository.findByStudentId(studentId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    // Récupérer une note précise
    @Transactional(readOnly = true)
    public GradeResponse getGradeById(
            Long studentId,
            Long gradeId) {

        Grade grade = findGrade(studentId, gradeId);

        if (grade == null) {
            return null;
        }

        return toResponse(grade);
    }

    // Modifier une note
    public GradeResponse updateGrade(
            Long studentId,
            Long gradeId,
            GradeRequest request) {

        Grade grade = findGrade(studentId, gradeId);

        if (grade == null) {
            return null;
        }

        grade.setSubject(request.subject());
        grade.setValue(request.value());

        Grade updatedGrade = gradeRepository.save(grade);

        return toResponse(updatedGrade);
    }

    // Supprimer une note
    public void deleteGrade(Long studentId, Long gradeId) {

        Grade grade = findGrade(studentId, gradeId);

        if (grade == null) {
            return;
        }

        gradeRepository.delete(grade);
    }

    // Vérifier qu'une note appartient bien à l'étudiant
    private Grade findGrade(Long studentId, Long gradeId) {

        return gradeRepository
            .findByIdAndStudentId(gradeId, studentId)
            .orElse(null);
    }

    
    // Calculer la moyenne d'un étudiant
    @Transactional(readOnly = true)
    public GradeAverageResponse getAverageByStudent(Long studentId) {

        // Vérifier que l'étudiant existe
        if (!studentRepository.existsById(studentId)) {
            return null;
        }

        // Récupérer toutes les notes de l'étudiant
        List<Grade> grades = gradeRepository.findByStudentId(studentId);

        // Calculer la moyenne
        double average = grades.stream()
            .mapToDouble(Grade::getValue)
            .average()
            .orElse(0.0);

        // Arrondir à deux chiffres après la virgule
        average = Math.round(average * 100.0) / 100.0;

        // Retourner le résultat
        return new GradeAverageResponse(
            studentId,
            average,
            grades.size()
        );
    }

}
