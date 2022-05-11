package org.example.repository;

import org.example.entity.Beer;
import org.example.entity.Brewery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BreweryRepository extends JpaRepository<Brewery, String>{

    /**
     * @param emf   thread safe factory which will be used for creating {@link EntityManager}
     */
    public BreweryRepository(EntityManagerFactory emf) {
        super(emf, Brewery.class);
    }

    public List<Brewery> findAllWithHeightGraterThan(int height){
        EntityManager em = getEmf().createEntityManager();
        List<Brewery> list = em.createQuery("select tower from Tower tower where tower.height > " + height, Brewery.class)
                //.setParameter("height", height)
                .getResultList();
        em.close();
        return list;
    }

}
