package ru.mirea.pr14.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.pr14.models.Departure;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DepartureService {
    private final Map<Long, List<Departure>> database = new HashMap<>();
    private final PostOfficeService postOfficeService;
    private static long ID = 0;

    public Map<Long, List<Departure>> getAll() {
        return database;
    }

    public long getCountByPostId(long postId) {
        Optional<List<Departure>> o = Optional.ofNullable(database.get(postId));
        return o.map(List::size).orElse(0);
    }

    public boolean save(long postId, Departure departure) {
        if (!postOfficeService.exist(postId)) return false;
        Optional<List<Departure>> departures = Optional.ofNullable(database.get(postId));
        departure.setId(ID++);

        if (departures.isPresent()) {
            departures.get().add(departure);
        } else {
            List<Departure> list = new ArrayList<Departure>() {{ add(departure); }};
            database.put(postId, list);
        }

        return true;
    }

    public void remove(long id) {
        database.values().forEach(l -> l.removeIf(d -> {
            if (d.getId() == id) {
                ID--;
                return true;
            }

            return false;
        }));
        database.entrySet().removeIf(e -> e.getValue().size() == 0);
    }
}
