package ru.mirea.pr17.repositories;

import ru.mirea.pr17.models.PostOffice;

import java.util.List;

public interface PostOfficeRepositoryFilter {
    List<PostOffice> findPostOfficeByNameAndCityName(String name, String cityName);
}
