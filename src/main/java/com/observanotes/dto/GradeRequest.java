
package com.observanotes.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GradeRequest(

    @NotBlank(message = "La matière est obligatoire")
    @Size(max = 100, message = "La matière ne doit pas dépasser 100 caractères")
    String subject,

    @NotNull(message = "La note est obligatoire")
    @DecimalMin(value = "0.0", message = "La note minimale est 0")
    @DecimalMax(value = "20.0", message = "La note maximale est 20")
    Double value

) {}
