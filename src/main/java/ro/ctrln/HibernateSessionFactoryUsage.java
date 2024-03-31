package ro.ctrln;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ro.ctrln.entities.Battleship;
import ro.ctrln.utils.SessionFactoryUtils;

import java.util.Iterator;

public class HibernateSessionFactoryUsage {

    public static void main(String[] args) {

        Session session = SessionFactoryUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Battleship battleship = new Battleship();
        battleship.setBattleshipName("Battleship");
        battleship.setBattleshipCapacity(1000);

        Battleship battleshipOne = new Battleship();
        battleshipOne.setBattleshipName("BattleshipOne");
        battleshipOne.setBattleshipCapacity(5000);

        session.save(battleship);
        session.save(battleshipOne);
//        session.getTransaction().commit();
//
//        session.beginTransaction();
        NativeQuery<Battleship> battleshipNativeQuery = session.createSQLQuery("select * from battleships");
        battleshipNativeQuery.addEntity(Battleship.class);
        iterateBattleships(battleshipNativeQuery.list().iterator());

        Query<Battleship> nameQuery = getBattleships(session);      // Accesam (entitatile noastre) data de baze in alt mod
        iterateBattleships(battleshipNativeQuery.list().iterator());

        NativeQuery namedParamiterQuery = session
                .createNativeQuery(
                        "select * from battleships where battleship_name= :battleshipName",
                        "BattleshipMapping");
        namedParamiterQuery.setParameter("battleshipName","BattleshipOne");
        iterateBattleships(namedParamiterQuery.list().iterator());

        NativeQuery updateQuery = session.createNativeQuery("update battleships set battleship_name = :battleshipName where id = :id");
        updateQuery.setParameter("battleshipName","ImperialStarDestroyer");
        updateQuery.setParameter("id",1);
        int result = updateQuery.executeUpdate();
        System.out.println(result + " row(s) was updated");
        session.getTransaction().commit();
        session.close();

        session = SessionFactoryUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Battleship> selectQuery = getBattleships(session);
        iterateBattleships(selectQuery.list().iterator());                      //ar trebui sa le puna din nou in rind dar nu le pune,de ce?

        session.getTransaction().commit();

        session.close();
    }

    private static Query<Battleship> getBattleships(Session session) {
        return session.createNamedQuery("selectBattleships", Battleship.class);
    }

    private static void iterateBattleships(Iterator<Battleship> iterator) {
        while (iterator.hasNext()){
            Battleship dbBattleship = iterator.next();
            System.out.println(dbBattleship);
        }
        System.out.println("================================================================================");
    }
}
