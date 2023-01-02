package mindata.delmoralcristian.superhero.dto;

import lombok.*;
import mindata.delmoralcristian.superhero.model.SuperHero;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperHeroRequest {

    private String name;
    private String power;
    private int attack;
    private int defense;
    private int age;
    private int health;
    private boolean isDeath;

    public static SuperHero of(long superheroId, SuperHeroRequest superHeroRequest) {
        return SuperHero.builder()
                .id(superheroId)
                .name(superHeroRequest.getName())
                .health(superHeroRequest.getHealth())
                .death(superHeroRequest.isDeath())
                .age(superHeroRequest.getAge())
                .power(superHeroRequest.getPower())
                .attack(superHeroRequest.getAttack())
                .defense(superHeroRequest.getDefense())
                .build();
    }
}
