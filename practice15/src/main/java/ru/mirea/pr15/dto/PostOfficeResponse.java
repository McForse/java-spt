package ru.mirea.pr15.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.mirea.pr15.models.Departure;
import ru.mirea.pr15.models.PostOffice;

import java.util.Set;

@Getter
@NoArgsConstructor
public class PostOfficeResponse {
    private long id;
    private String name;
    private String cityName;
    private Set<Departure> departures;

    public PostOfficeResponse(PostOffice office) {
        id = office.getId();
        name = office.getName();
        cityName = office.getCityName();
        departures = office.getDepartures();
    }
}
