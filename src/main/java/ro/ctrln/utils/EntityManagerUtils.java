package ro.ctrln.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("ro.ctrln.entities_catalog");
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
