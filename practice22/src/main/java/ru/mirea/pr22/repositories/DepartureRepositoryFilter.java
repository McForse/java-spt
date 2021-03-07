package ru.mirea.pr22.repositories;

import ru.mirea.pr22.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
