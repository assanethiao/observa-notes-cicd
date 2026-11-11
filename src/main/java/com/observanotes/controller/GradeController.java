
package com.observanotes.controller;

import com.observanotes.dto.GradeRequest;
import com.observanotes.dto.GradeResponse;
import com.observanotes.service.GradeService;
import com.observanotes.dto.GradeAverageResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // Ajouter une note
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GradeResponse createGrade(
            @PathVariable Long studentId,
            @Valid @RequestBody GradeRequest request) {

        return gradeService.createGrade(studentId, request);
    }

    // Récupérer toutes les notes d'un étudiant
    @GetMapping
    public List<GradeResponse> getGradesByStudent(
            @PathVariable Long studentId) {

        return gradeService.getGradesByStudent(studentId);
    }

    // Récupérer une note
    @GetMapping("/{gradeId}")
    public GradeResponse getGradeById(
            @PathVariable Long studentId,
            @PathVariable Long gradeId) {

        return gradeService.getGradeById(studentId, gradeId);
    }

    // Modifier une note
    @PutMapping("/{gradeId}")
    public GradeResponse updateGrade(
            @PathVariable Long studentId,
            @PathVariable Long gradeId,
            @Valid @RequestBody GradeRequest request) {

        return gradeService.updateGrade(studentId, gradeId, request);
    }

    // Supprimer une note
    @DeleteMapping("/{gradeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGrade(
            @PathVariable Long studentId,
            @PathVariable Long gradeId) {

        gradeService.deleteGrade(studentId, gradeId);
    }

    
    // Calculer la moyenne d'un étudiant
    @GetMapping("/average")
    public GradeAverageResponse getAverageByStudent(
            @PathVariable Long studentId) {

        return gradeService.getAverageByStudent(studentId);
    }

}
