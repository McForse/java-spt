package ru.mirea.pr17.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class DepartureRequest {
    @NotBlank
    private final String type;
    @NotBlank
    private final String date;
    @NotNull
    private final Long postId;
}
