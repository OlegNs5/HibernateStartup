package ro.ctrln.utils;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;
import ro.ctrln.entities.User;

import javax.persistence.EntityManager;

public class OneToOneUsage {
    public static void main(String[] args){

    User user = new User();
    user.setEmail("test@bittnet.ro");
    user.setUsername ("Test Bittnet");
//    user.setPassword("******");

//    Address address = new Address();
//    address.setStreetName ("Revolutiei 1989");
//    address.setStreetNumben ("1");
//    address.setUser (user);
//    user.setHomeAddress (address);

    EntityManager em = EntityManagerUtils.getEntityManager();
    em.getTransaction().begin();
    em.persist(user);
//    em.persist(address);
    em.getTransaction().commit();

    em.getTransaction().begin();
    User dbUser = em.find (User.class, 1L);
    Address dbAddress = em.find(Address.class, 2L);
        System.out.println(dbUser);
        System.out.println(dbAddress);
}
}
