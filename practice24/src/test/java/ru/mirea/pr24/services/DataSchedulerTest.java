package ru.mirea.pr24.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataSchedulerTest {
    private DataScheduler dataScheduler;
    @MockBean
    private PostOfficeService postOfficeService;
    @MockBean
    private DepartureService departureService;
    @Value("${dir.data}")
    private String path;

    @Autowired
    public void setDataScheduler(DataScheduler dataScheduler) {
        this.dataScheduler = dataScheduler;
    }

    @Test
    public void checkDataFilesExist() throws IOException {
        dataScheduler.saveData();

        File dir = ResourceUtils.getFile(path);
        verify(postOfficeService, times(1)).getAll();
        verify(departureService, times(1)).getAll();
        assertTrue(dir.exists());
        String[] files = {"post_offices.txt", "departures.txt"};

        assertTrue(Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .map(File::getName)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList(files)));
    }
}
