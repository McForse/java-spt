package ru.mirea.pr14.services;

import org.springframework.stereotype.Service;
import ru.mirea.pr14.models.PostOffice;

import java.util.*;

@Service
public class PostOfficeService {
    private final List<PostOffice> database = new ArrayList<>();
    private static long ID = 0;

    public List<PostOffice> getAll() {
        return database;
    }

    public void save(PostOffice office) {
        office.setId(ID++);
        database.add(office);
    }

    public void remove(long id) {
        database.removeIf(o -> o.getId() == id);
    }

    public boolean exist(long id) {
        return database.stream().anyMatch(p -> p.getId() == id);
    }
}
