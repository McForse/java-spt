package ru.mirea.pr23.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.pr23.models.PostOffice;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long>, PostOfficeRepositoryFilter {
}
