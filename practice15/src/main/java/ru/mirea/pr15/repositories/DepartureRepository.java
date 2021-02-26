package ru.mirea.pr15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.pr15.models.Departure;
import ru.mirea.pr15.models.PostOffice;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long> {
    void deleteAllByPost(PostOffice postOffice);
}
