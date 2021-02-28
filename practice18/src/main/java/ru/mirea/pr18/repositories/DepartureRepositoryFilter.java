package ru.mirea.pr18.repositories;

import ru.mirea.pr18.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
