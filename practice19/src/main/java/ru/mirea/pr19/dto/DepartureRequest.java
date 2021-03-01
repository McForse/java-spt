package ru.mirea.pr19.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@AllArgsConstructor
public class DepartureRequest {
    @NotBlank
    private final String type;
    @NotBlank
    private final String date;
    @NotNull
    private final Long postId;
}
