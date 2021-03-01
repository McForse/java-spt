package ru.mirea.pr20.repositories;

import ru.mirea.pr20.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
