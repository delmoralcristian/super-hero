package mindata.delmoralcristian.superhero.dao;

import mindata.delmoralcristian.superhero.model.SuperHero;

import java.util.List;
import java.util.Optional;

public interface SuperHeroManagementDao {

    Optional<SuperHero> getSuperHero(long superheroId);

    Iterable<SuperHero> getAllSuperheroes();

    List<SuperHero> getAllSuperheroesByName(String name);

    void deleteSuperHero(long superheroId);

    SuperHero updateSuperhero(SuperHero superHero);
}
