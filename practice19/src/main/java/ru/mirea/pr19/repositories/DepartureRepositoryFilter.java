package ru.mirea.pr19.repositories;

import ru.mirea.pr19.models.Departure;

import java.util.List;

public interface DepartureRepositoryFilter {
    List<Departure> findDeparturesByTypeAndDate(String type, String date);
}
