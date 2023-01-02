package mindata.delmoralcristian.superhero.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "superhero")
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperHero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String power;

    @Column
    private int attack;

    @Column
    private int defense;

    @Column
    private int age;

    @Column
    private int health;

    @Column
    private boolean death;

}
