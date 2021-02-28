package ru.mirea.pr18.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.pr18.exceptions.NotFoundException;
import ru.mirea.pr18.models.PostOffice;
import ru.mirea.pr18.dto.PostOfficeRequest;
import ru.mirea.pr18.repositories.DepartureRepository;
import ru.mirea.pr18.repositories.PostOfficeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;
    private final DepartureRepository departureRepository;

    @Transactional
    public <T> T getAll(Function<List<PostOffice>, T> dtoConverter) {
        return dtoConverter.apply(postOfficeRepository.findAll());
    }

    @Transactional
    public <T> T getFiltered(String name, String cityName, Function<List<PostOffice>, T> dtoConverter) {
        return dtoConverter.apply(postOfficeRepository.findPostOfficeByNameAndCityName(name, cityName));
    }

    public void save(PostOfficeRequest post) {
        PostOffice office = PostOffice.builder()
                .name(post.getName())
                .cityName(post.getCityName())
                .build();

        postOfficeRepository.save(office);
    }

    public Optional<PostOffice> getById(long id) {
        return postOfficeRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
        Optional<PostOffice> post = getById(id);
        if (!post.isPresent()) throw new NotFoundException(String.format("Post office with %s not found when deleting", id));
        departureRepository.deleteAllByPost(post.get());
        postOfficeRepository.deleteById(id);
    }
}
