package mindata.delmoralcristian.superhero.dto;

import lombok.*;
import mindata.delmoralcristian.superhero.model.SuperHero;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperHeroResponse {

    private long id;
    private String name;
    private String power;
    private int attack;
    private int defense;
    private int age;
    private int health;
    private boolean isDeath;

    public static SuperHeroResponse of(SuperHero superHero) {
        return SuperHeroResponse.builder()
                .id(superHero.getId())
                .name(superHero.getName())
                .health(superHero.getHealth())
                .isDeath(superHero.isDeath())
                .age(superHero.getAge())
                .power(superHero.getPower())
                .attack(superHero.getAttack())
                .defense(superHero.getDefense())
                .build();
    }

    public static List<SuperHeroResponse> of(List<SuperHero> superHeroes) {
        return superHeroes.stream()
                        .map(superHero -> SuperHeroResponse.of(superHero))
                        .collect(Collectors.toList());
    }

}
