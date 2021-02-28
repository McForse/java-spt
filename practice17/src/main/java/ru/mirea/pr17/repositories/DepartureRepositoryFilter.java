package ru.mirea.pr17.repositories;

import ru.mirea.pr17.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
