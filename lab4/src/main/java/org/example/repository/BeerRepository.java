package org.example.repository;

import org.example.entity.Beer;
import org.example.entity.Brewery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BeerRepository extends JpaRepository<Beer, String> {

    /**
     * @param emf   thread safe factory which will be used for creating {@link EntityManager}
     */
    public BeerRepository(EntityManagerFactory emf) {
        super(emf, Beer.class);
    }

    public List<Beer> findAllWithPriceLowerThan(long price){
        EntityManager em = getEmf().createEntityManager();
        List<Beer> list = em.createQuery("select beer from Beer beer where beer.price < " + price, Beer.class)
                .getResultList();
        em.close();
        return list;
    }

    public List<Beer> findAllFromBreweryCheaperThan(String breweryName, long price){
        EntityManager em = getEmf().createEntityManager();
        List<Beer> list = em.createQuery("select beer from Beer beer where beer.brewery :brewery= ", Beer.class)
                //.setParameter("brewery", brewery)
                .getResultList();
        em.close();
        return list;
    }

    public List<Beer> findAllFromBrewery(Brewery brewery){
        EntityManager em = getEmf().createEntityManager();
        List<Beer> list = em.createQuery("select beer from Beer beer where beer.brewery = :brewery=", Beer.class)
                .setParameter("brewery", brewery)
                .getResultList();
        em.close();
        return list;
    }
}
