package ru.mirea.pr21.repositories;

import ru.mirea.pr21.models.PostOffice;

import java.util.List;

public interface PostOfficeRepositoryFilter {
    List<PostOffice> findPostOfficeByNameAndCityName(String name, String cityName);
}
