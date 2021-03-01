package ru.mirea.pr20.repositories;

import ru.mirea.pr20.models.PostOffice;

import java.util.List;

public interface PostOfficeRepositoryFilter {
    List<PostOffice> findPostOfficeByNameAndCityName(String name, String cityName);
}
