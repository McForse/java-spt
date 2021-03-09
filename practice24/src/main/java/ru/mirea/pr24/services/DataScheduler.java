package ru.mirea.pr24.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataScheduler {
    private final PostOfficeService postOfficeService;
    private final DepartureService departureService;
    @Value("${dir.data}")
    private String path;

    @Scheduled(cron = "0 0/30 * * * *")
    public void saveData() throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        log.info("Scheduled task started at {}", format.format(date));

        File dir = ResourceUtils.getFile(path);

        if (dir.exists()) {
            FileUtils.cleanDirectory(dir);
            log.info("Data directory was cleaned up successfully");
        } else {
            if (dir.mkdir()) {
                log.info("Data directory was created successfully");
            } else {
                log.error("Data directory was not created");
                return;
            }
        }

        String absPath = dir.getAbsolutePath();
        savePostOffices(absPath);
        saveDepartures(absPath);
        log.info("Scheduled task finished");
    }

    private void savePostOffices(String path) throws IOException {
        BufferedWriter writer = createWriter(path, "post_offices.txt");
        writer.write("id|name|city_name\n");
        postOfficeService.getAll()
                .forEach(office -> {
                            try {
                                writer.write(
                                        String.format("%d|%s|%s\n", office.getId(), office.getName(), office.getCityName())
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
        writer.close();
    }

    private void saveDepartures(String path) throws IOException {
        BufferedWriter writer = createWriter(path, "departures.txt");
        writer.write("id|type|date|post_id\n");
        departureService.getAll()
                .forEach(departure -> {
                            try {
                                writer.write(
                                        String.format(
                                                "%d|%s|%s|%d\n",
                                                departure.getId(),
                                                departure.getType(),
                                                departure.getDate(),
                                                departure.getPost().getId()
                                        )
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
        writer.close();
    }

    private BufferedWriter createWriter(String dir, String filename) throws IOException {
        return new BufferedWriter(new FileWriter(String.format("%s/%s", dir, filename)));
    }
}
