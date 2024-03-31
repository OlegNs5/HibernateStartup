package ro.ctrln.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(schema="",name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="product_name")
    private String productName;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();


}
