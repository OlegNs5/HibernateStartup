package ro.ctrln.utils;

import ro.ctrln.entities.Order;
import ro.ctrln.entities.User;
import ro.ctrln.utils.EntityManagerUtils;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class OneToManyUsage {
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("test@bittnet.ro");
        user.setUsername ("Test Bittnet");
//    user.setPassword("******");

        Order orderOne = new Order();
        orderOne.setOrderDate(new Date());

        Order orderTwo = new Order();
        orderTwo.setOrderDate(Date.from(LocalDate.now().minusDays(3).atStartOfDay().toInstant(ZoneOffset.UTC)));

        user.addOrder(orderOne);
        user.addOrder(orderTwo);

        EntityManager em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.getTransaction().begin();
        User dbUser = em.find(User.class,1L);
//        List<Order> dbOrders = dbUser.getOrders();
//        System.out.println(dbOrders);

    }
}
