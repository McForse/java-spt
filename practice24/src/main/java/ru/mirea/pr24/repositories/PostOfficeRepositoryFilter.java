package ru.mirea.pr24.repositories;

import ru.mirea.pr24.models.PostOffice;

import java.util.List;

public interface PostOfficeRepositoryFilter {
    List<PostOffice> findPostOfficeByNameAndCityName(String name, String cityName);
}
