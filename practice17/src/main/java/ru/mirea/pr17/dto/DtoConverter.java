package ru.mirea.pr17.dto;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import ru.mirea.pr17.models.Departure;
import ru.mirea.pr17.models.PostOffice;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    public DepartureResponse toDepartureResponse(Departure departure) {
        DepartureResponse response = new DepartureResponse();
        response.setId(departure.getId());
        response.setType(departure.getType());
        response.setDate(departure.getDate());
        return response;
    }

    public List<DepartureResponse> toDepartureResponseList(List<Departure> departures) {
        return departures.stream()
                .map(this::toDepartureResponse)
                .collect(Collectors.toList());
    }

    public PostOfficeResponse toPostOfficeResponse(PostOffice office) {
        PostOfficeResponse response = new PostOfficeResponse();
        response.setId(office.getId());
        response.setName(office.getName());
        response.setCityName(office.getCityName());
        response.setDepartures(toDepartureResponseList(new ArrayList<>(office.getDepartures())));
        return response;
    }

    public List<PostOfficeResponse> toPostOfficeResponseList(List<PostOffice> offices) {
        return offices.stream()
                .map(this::toPostOfficeResponse)
                .collect(Collectors.toList());
    }
}
