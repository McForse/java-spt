package ru.mirea.pr15.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.pr15.exceptions.NotFoundException;
import ru.mirea.pr15.models.Departure;
import ru.mirea.pr15.models.PostOffice;
import ru.mirea.pr15.dto.DepartureRequest;
import ru.mirea.pr15.repositories.DepartureRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeService postOfficeService;

    public void save(DepartureRequest departure) {
        Optional<PostOffice> officeOptional = postOfficeService.getById(departure.getPostId());

        if (!officeOptional.isPresent()) {
            throw new NotFoundException(String.format("Post office with %s not found when adding departure", departure.getPostId()));
        }

        PostOffice office = officeOptional.get();

        Departure dep = Departure.builder()
                .type(departure.getType())
                .date(departure.getDate())
                .post(office)
                .build();

        departureRepository.save(dep);
    }

    public void delete(long id) {
        Optional<Departure> departure = departureRepository.findById(id);
        if (!departure.isPresent()) throw new NotFoundException(String.format("Departure with %s not found when deleting", id));
        departureRepository.deleteById(id);
    }
}
