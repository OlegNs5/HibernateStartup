package ro.ctrln.entities;


import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Table(schema = "",name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY/*,optional = false*/)
    private Address homeAddress;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public void addOrder (Order order){
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder (Order order){
        orders.remove(order);
        order.setUser(null);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", homeAddress=" + homeAddress +
                '}';
    }
}
