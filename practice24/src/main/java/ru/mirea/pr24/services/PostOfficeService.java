package ru.mirea.pr24.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.pr24.exceptions.NotFoundException;
import ru.mirea.pr24.models.PostOffice;
import ru.mirea.pr24.dto.PostOfficeRequest;
import ru.mirea.pr24.repositories.DepartureRepository;
import ru.mirea.pr24.repositories.PostOfficeRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;
    private final DepartureRepository departureRepository;

    public List<PostOffice> getAll() {
        return postOfficeRepository.findAll();
    }

    public <T> T getAll(Function<List<PostOffice>, T> dtoConverter) {
        log.info("Find all departures");
        return dtoConverter.apply(postOfficeRepository.findAll());
    }

    public <T> T getFiltered(String name, String cityName, Function<List<PostOffice>, T> dtoConverter) {
        log.info("Find post offices by filter: name = {}; cityName = {}", name, cityName);
        return dtoConverter.apply(postOfficeRepository.findPostOfficeByNameAndCityName(name, cityName));
    }

    @Transactional
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
