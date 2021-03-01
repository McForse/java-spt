package ru.mirea.pr19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.pr19.models.PostOffice;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long>, PostOfficeRepositoryFilter {
}
