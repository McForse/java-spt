package ru.mirea.pr19.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.pr19.exceptions.NotFoundException;
import ru.mirea.pr19.models.PostOffice;
import ru.mirea.pr19.dto.PostOfficeRequest;
import ru.mirea.pr19.repositories.DepartureRepository;
import ru.mirea.pr19.repositories.PostOfficeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;
    private final DepartureRepository departureRepository;

    @Transactional
    public <T> T getAll(Function<List<PostOffice>, T> dtoConverter) {
        log.info("Find all departures");
        return dtoConverter.apply(postOfficeRepository.findAll());
    }

    @Transactional
    public <T> T getFiltered(String name, String cityName, Function<List<PostOffice>, T> dtoConverter) {
        log.info("Find post offices by filter: name = {}; cityName = {}", name, cityName);
        return dtoConverter.apply(postOfficeRepository.findPostOfficeByNameAndCityName(name, cityName));
    }

    public void save(PostOfficeRequest post) {
        log.info("Save post office {}", post);
        PostOffice office = PostOffice.builder()
                .name(post.getName())
                .cityName(post.getCityName())
                .build();

        postOfficeRepository.save(office);
    }

    public Optional<PostOffice> getById(long id) {
        log.info("Find post office by id {}", id);
        return postOfficeRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
        log.info("Delete post office with id {}", id);
        Optional<PostOffice> post = getById(id);
        if (!post.isPresent()) throw new NotFoundException(String.format("Post office with %s not found when deleting", id));
        departureRepository.deleteAllByPost(post.get());
        postOfficeRepository.deleteById(id);
    }
}
