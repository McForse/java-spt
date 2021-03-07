package ru.mirea.pr22.dto;

import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@ToString
public class PostOfficeResponse {
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String cityName;
    private List<DepartureResponse> departures;
}
