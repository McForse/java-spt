package ru.mirea.pr17.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.pr17.exceptions.NotFoundException;
import ru.mirea.pr17.models.Departure;
import ru.mirea.pr17.models.PostOffice;
import ru.mirea.pr17.dto.DepartureRequest;
import ru.mirea.pr17.repositories.DepartureRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeService postOfficeService;

    @Transactional
    public <T> T getAll(Function<List<Departure>, T> dtoConverter) {
        return dtoConverter.apply(departureRepository.findAll());
    }

    @Transactional
    public <T> T getFiltered(String type, String date, Function<List<Departure>, T> dtoConverter) {
        return dtoConverter.apply(departureRepository.findDeparturesByTypeAndDate(type, date));
    }

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
