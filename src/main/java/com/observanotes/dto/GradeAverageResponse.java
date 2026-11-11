
package com.observanotes.dto;

public record GradeAverageResponse(
    Long studentId,
    Double average,
    int numberOfGrades
) {}
