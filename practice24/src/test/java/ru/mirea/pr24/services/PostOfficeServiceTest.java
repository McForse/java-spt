package ru.mirea.pr24.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mirea.pr24.dto.PostOfficeRequest;
import ru.mirea.pr24.models.PostOffice;
import ru.mirea.pr24.repositories.DepartureRepository;
import ru.mirea.pr24.repositories.PostOfficeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostOfficeServiceTest {
    @MockBean
    private PostOfficeRepository postOfficeRepository;
    @MockBean
    private DepartureRepository departureRepository;
    private PostOfficeService postOfficeService;
    @Captor
    private ArgumentCaptor<PostOffice> captor;
    private final List<PostOffice> offices = new ArrayList<>();

    @Autowired
    public void setPostOfficeService(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @Before
    public void setUp() {
        PostOffice office1 = new PostOffice();
        office1.setName("Office1");
        office1.setCityName("Name1");
        PostOffice office2 = new PostOffice();
        office2.setName("Office2");
        office2.setCityName("Name2");
        offices.add(office1);
        offices.add(office2);

        when(postOfficeRepository.findAll())
                .thenReturn(offices);
    }

    @Test
    public void whenGetAll() {
        List<PostOffice> fetched = postOfficeService.getAll();
        verify(postOfficeRepository, times(1))
                .findAll();
        assertEquals(offices.size(), fetched.size());
    }

    @Test
    public void whenValidSave() {
        PostOffice office = new PostOffice();
        office.setName("Post");
        office.setCityName("Moscow");
        postOfficeService.save(toRequest(office));

        verify(postOfficeRepository, times(1))
                .save(office);
        when(postOfficeRepository.findAll())
                .thenReturn(Collections.singletonList(office));
        verify(postOfficeRepository).save(captor.capture());
        PostOffice captured = captor.getValue();
        assertEquals(office.getName(), captured.getName());
        assertEquals(office.getCityName(), captured.getCityName());
    }

    @Test
    public void whenDelete() {
        PostOffice office = new PostOffice();
        office.setName("Post");
        office.setCityName("Moscow");
        postOfficeService.save(toRequest(office));

        verify(postOfficeRepository, times(1))
                .save(office);
        verify(postOfficeRepository).save(captor.capture());
        PostOffice captured = captor.getValue();

        when(postOfficeRepository.findById(captured.getId()))
                .thenReturn(Optional.of(captured));

        postOfficeService.delete(captured.getId());
        verify(postOfficeRepository, times(1))
                .deleteById(captured.getId());
    }

    private PostOfficeRequest toRequest(PostOffice office) {
        return new PostOfficeRequest(office.getName(), office.getCityName());
    }
}
