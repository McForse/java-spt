package ru.mirea.pr17.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class PostOfficeRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final String cityName;
}
