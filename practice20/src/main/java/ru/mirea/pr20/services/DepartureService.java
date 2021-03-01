package ru.mirea.pr20.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.pr20.exceptions.NotFoundException;
import ru.mirea.pr20.models.Departure;
import ru.mirea.pr20.models.PostOffice;
import ru.mirea.pr20.dto.DepartureRequest;
import ru.mirea.pr20.repositories.DepartureRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeService postOfficeService;

    @Transactional
    public <T> T getAll(Function<List<Departure>, T> dtoConverter) {
        log.info("Find all departures");
        return dtoConverter.apply(departureRepository.findAll());
    }

    @Transactional
    public <T> T getFiltered(String type, String date, Function<List<Departure>, T> dtoConverter) {
        log.info("Find departures by filter: type = {}; date = {}", type, date);
        return dtoConverter.apply(departureRepository.findDeparturesByTypeAndDate(type, date));
    }

    public void save(DepartureRequest departure) {
        log.info("Save departure {}", departure);
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
        log.info("Delete departure with id {}", id);
        Optional<Departure> departure = departureRepository.findById(id);
        if (!departure.isPresent()) throw new NotFoundException(String.format("Departure with %s not found when deleting", id));
        departureRepository.deleteById(id);
    }
}
