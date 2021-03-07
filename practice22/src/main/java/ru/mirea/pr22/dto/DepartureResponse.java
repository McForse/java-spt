package ru.mirea.pr22.dto;

import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@ToString
public class DepartureResponse {
    @NotNull
    private long id;
    @NotBlank
    private String type;
    @NotBlank
    private String date;
}
