
package com.observanotes.dto;

public record GradeResponse(
    Long id,
    String subject,
    Double value,
    Long studentId
) {}
