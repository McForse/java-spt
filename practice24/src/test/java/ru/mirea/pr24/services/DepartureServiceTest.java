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
import ru.mirea.pr24.dto.DepartureRequest;
import ru.mirea.pr24.models.Departure;
import ru.mirea.pr24.models.PostOffice;
import ru.mirea.pr24.repositories.DepartureRepository;
import ru.mirea.pr24.repositories.PostOfficeRepository;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartureServiceTest {
    @MockBean
    private PostOfficeRepository postOfficeRepository;
    @MockBean
    private DepartureRepository departureRepository;
    private DepartureService departureService;
    @Captor
    private ArgumentCaptor<Departure> captor;
    private PostOffice office;
    private final List<Departure> departures = new ArrayList<>();

    @Autowired
    public void setDepartureService(DepartureService departureService) {
        this.departureService = departureService;
    }

    @Before
    public void setUp() {
        office = new PostOffice();
        office.setId(1L);
        office.setName("Post");
        office.setCityName("Moscow");

        when(postOfficeRepository.findById(office.getId()))
                .thenReturn(Optional.of(office));

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Departure departure1 = new Departure();
        departure1.setType("Box");
        departure1.setDate(date);
        departure1.setPost(office);
        Departure departure2 = new Departure();
        departure2.setType("Box");
        departure2.setDate(date);
        departure2.setPost(office);
        departures.add(departure1);
        departures.add(departure2);

        when(departureRepository.findAll())
                .thenReturn(departures);
    }

    @Test
    public void whenGetAll() {
        List<Departure> fetched = departureService.getAll();
        verify(departureRepository, times(1))
                .findAll();
        assertEquals(departures.size(), fetched.size());
    }

    @Test
    public void whenValidSave() {
        Departure departure = new Departure();
        departure.setType("Box");
        departure.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        departure.setPost(office);
        departureService.save(toRequest(departure));

        verify(departureRepository).save(captor.capture());
        Departure captured = captor.getValue();
        assertEquals(departure.getType(), captured.getType());
        assertEquals(departure.getDate(), captured.getDate());
        assertEquals(departure.getPost().getId(), captured.getPost().getId());
    }

    @Test
    public void whenDelete() {
        Departure departure = new Departure();
        departure.setType("Box");
        departure.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        departure.setPost(office);
        departureService.save(toRequest(departure));

        verify(departureRepository).save(captor.capture());
        Departure captured = captor.getValue();

        when(departureRepository.findById(captured.getId()))
                .thenReturn(Optional.of(captured));

        departureService.delete(captured.getId());
        verify(departureRepository, times(1))
                .deleteById(captured.getId());
    }

    private DepartureRequest toRequest(Departure departure) {
        return new DepartureRequest(departure.getType(),
                departure.getDate(),
                departure.getPost().getId());
    }
}
