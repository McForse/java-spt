package ru.mirea.pr18.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.mirea.pr18.models.Departure;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartureRepositoryFilterImpl implements DepartureRepositoryFilter {
    private final EntityManager em;

    @Override
    public List<Departure> findDeparturesByTypeAndDate(String type, String date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Departure> cq = cb.createQuery(Departure.class);

        Root<Departure> departure = cq.from(Departure.class);
        List<Predicate> predicates = new ArrayList<>();

        if (type != null && !type.isEmpty()) {
            predicates.add(cb.equal(departure.get("type"), type));
        }

        if (date != null && !date.isEmpty()) {
            predicates.add(cb.like(departure.get("date"), date));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
