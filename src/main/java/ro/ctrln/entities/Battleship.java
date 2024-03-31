package ro.ctrln.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

//In Hibernate toate entitatile trebuie sa aiba un id
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "", name = "battleships")
@NamedNativeQuery(name = "selectBattleships",query = "* from battleships", resultSetMapping = "BattleshipMapping")
@SqlResultSetMapping(name = "BattleshipMapping",entities = @EntityResult(entityClass = Battleship.class))
public class Battleship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "battleship_name")
    private String battleshipName;

    @Column(name = "battleship_capacity")
    private int battleshipCapacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battleship that = (Battleship) o;
        return id == that.id && battleshipCapacity == that.battleshipCapacity && Objects.equals(battleshipName, that.battleshipName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, battleshipName, battleshipCapacity);
    }

    @Override
    public String toString() {
        return "Battleship{" +
                "id=" + id +
                ", battleshipName='" + battleshipName + '\'' +
                ", battleshipCapacity=" + battleshipCapacity +
                '}';
    }
}
