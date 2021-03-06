package ru.mirea.pr21.repositories;

import ru.mirea.pr21.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
