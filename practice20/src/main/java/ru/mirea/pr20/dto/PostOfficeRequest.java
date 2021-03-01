package ru.mirea.pr20.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@AllArgsConstructor
public class PostOfficeRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final String cityName;
}
