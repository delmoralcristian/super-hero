package mindata.delmoralcristian.superhero.service;

import mindata.delmoralcristian.superhero.dto.SuperHeroRequest;
import mindata.delmoralcristian.superhero.exceptions.NotFoundException;
import mindata.delmoralcristian.superhero.model.SuperHero;

import java.util.List;

public interface SuperHeroManagementService {

    SuperHero getSuperHero(long superheroId) throws NotFoundException;

    List<SuperHero> getAllSuperheroes();

    List<SuperHero> getAllSuperheroesByName(String name);

    void deleteSuperHero(long superheroId) throws NotFoundException;

    SuperHero updateSuperhero(long superheroId, SuperHeroRequest superHeroRequest) throws NotFoundException;
}
