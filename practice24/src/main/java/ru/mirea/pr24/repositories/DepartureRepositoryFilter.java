package ru.mirea.pr24.repositories;

import ru.mirea.pr24.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
