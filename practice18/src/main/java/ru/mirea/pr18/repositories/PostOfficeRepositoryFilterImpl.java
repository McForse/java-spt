package ru.mirea.pr18.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.mirea.pr18.models.PostOffice;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostOfficeRepositoryFilterImpl implements PostOfficeRepositoryFilter {
    private final EntityManager em;

    @Override
    public List<PostOffice> findPostOfficeByNameAndCityName(String name, String cityName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PostOffice> cq = cb.createQuery(PostOffice.class);

        Root<PostOffice> postOffice = cq.from(PostOffice.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.equal(postOffice.get("name"), name));
        }

        if (cityName != null && !cityName.isEmpty()) {
            predicates.add(cb.equal(postOffice.get("cityName"), cityName));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
