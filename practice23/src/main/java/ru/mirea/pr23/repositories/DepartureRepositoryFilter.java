package ru.mirea.pr23.repositories;

import ru.mirea.pr23.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
