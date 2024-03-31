package ro.ctrln;

import ro.ctrln.entities.Battleship;
import ro.ctrln.utils.EntityManagerUtils;

import javax.persistence.EntityManager;

public class HibernateEntityManagerUsage {

    public static void main(String[] args) {

        Battleship battleship = new Battleship();
        battleship.setBattleshipName("Battleship!");
        battleship.setBattleshipCapacity(1000);

        Battleship battleshipTwo = new Battleship();
        battleshipTwo.setBattleshipName("BattleshipTwo");
        battleshipTwo.setBattleshipCapacity(5000);

        saveBattleship(battleship);
        saveBattleship(battleshipTwo);

        System.out.println(findBattleshipById(1L));
        System.out.println(findBattleshipById(2L));

        battleship.setBattleshipName("BattleshipUpdated");
        battleship.setBattleshipCapacity(1005);
        updateBattleship(battleship);
        System.out.println(findBattleshipById(battleship.getId()));

        removeBattleship(2L);
        System.out.println("Entitate stearsa " + findBattleshipById(2L));
    }

    private static void removeBattleship(long battleshipId) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        Battleship battleship = em.find(Battleship.class, battleshipId);
//        em.persist(battleshipId);
        em.remove(battleshipId);
        em.getTransaction().commit();

    }


    private static void updateBattleship(Battleship battleship) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        Battleship dbBattleship = findBattleshipById(battleship.getId()); //caut itentitatea mea in baza de date
        em.detach(dbBattleship);
        dbBattleship.setBattleshipName(battleship.getBattleshipName());
        dbBattleship.setBattleshipCapacity(battleship.getBattleshipCapacity());
        em.getTransaction().begin();
        em.merge(dbBattleship);
        em.getTransaction().commit();
    }


    private static void saveBattleship(Battleship battleship) {
        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(battleship);
        em.getTransaction().commit();
    }

    private static Battleship findBattleshipById(Long id){
        EntityManager em = EntityManagerUtils.getEntityManager();
//        em.getTransaction().begin();
        /*Battleship battleshipFromDB = */em.find(Battleship.class,id); //se poate si fara a crea o noua variabila locala
        return em.find(Battleship.class,id);
    }
}
