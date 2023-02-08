package com.nasdev.estate.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FloorDto {

    private Long id;
    @Range(min = -3, max = 164, message = "Floor must be between -3 and 164.")
    private int number;
}
