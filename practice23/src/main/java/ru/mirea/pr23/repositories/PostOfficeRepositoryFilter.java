package ru.mirea.pr23.repositories;

import ru.mirea.pr23.models.PostOffice;

import java.util.List;

public interface PostOfficeRepositoryFilter {
    List<PostOffice> findPostOfficeByNameAndCityName(String name, String cityName);
}
