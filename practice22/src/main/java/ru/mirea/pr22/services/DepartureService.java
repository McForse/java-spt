package ru.mirea.pr22.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.pr22.exceptions.NotFoundException;
import ru.mirea.pr22.models.Departure;
import ru.mirea.pr22.models.PostOffice;
import ru.mirea.pr22.dto.DepartureRequest;
import ru.mirea.pr22.repositories.DepartureRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final PostOfficeService postOfficeService;
    private final EmailService emailService;

    public List<Departure> getAll() {
        return departureRepository.findAll();
    }

    public <T> T getAll(Function<List<Departure>, T> dtoConverter) {
        log.info("Find all departures");
        return dtoConverter.apply(departureRepository.findAll());
    }

    public <T> T getFiltered(String type, String date, Function<List<Departure>, T> dtoConverter) {
        log.info("Find departures by filter: type = {}; date = {}", type, date);
        return dtoConverter.apply(departureRepository.findDeparturesByTypeAndDate(type, date));
    }

    @Transactional
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
        emailService.send(
                "Departure insert",
                dep + " inserted in the database."
        );
    }

    @Transactional
    public void delete(long id) {
        log.info("Delete departure with id {}", id);
        Optional<Departure> departure = departureRepository.findById(id);
        if (!departure.isPresent()) throw new NotFoundException(String.format("Departure with %s not found when deleting", id));
        departureRepository.deleteById(id);
    }
}
