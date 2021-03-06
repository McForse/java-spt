package ru.mirea.pr21.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.pr21.models.Departure;
import ru.mirea.pr21.models.PostOffice;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long>, DepartureRepositoryFilter {
    void deleteAllByPost(PostOffice postOffice);
}
