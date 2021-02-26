package ru.mirea.pr15.services;

import org.springframework.stereotype.Service;
import ru.mirea.pr15.exceptions.NotFoundException;
import ru.mirea.pr15.models.PostOffice;
import ru.mirea.pr15.payload.requests.PostOfficeRequest;
import ru.mirea.pr15.repositories.DepartureRepository;
import ru.mirea.pr15.repositories.PostOfficeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;
    private final DepartureRepository departureRepository;

    public PostOfficeService(PostOfficeRepository postOfficeRepository, DepartureRepository departureRepository) {
        this.postOfficeRepository = postOfficeRepository;
        this.departureRepository = departureRepository;
    }

    public List<PostOffice> getAll() {
        return postOfficeRepository.findAll();
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
