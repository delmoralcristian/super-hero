package mindata.delmoralcristian.superhero.dao.impl;

import mindata.delmoralcristian.superhero.dao.SuperHeroManagementDao;
import mindata.delmoralcristian.superhero.model.SuperHero;
import mindata.delmoralcristian.superhero.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class SuperHeroManagementDaoImpl implements SuperHeroManagementDao {

    @Autowired
    private SuperHeroRepository superheroRepository;

    @Override
    public Optional<SuperHero> getSuperHero(long superheroId) {
        return superheroRepository.findById(superheroId);
    }

    @Override
    @Cacheable("superheroes")
    public Iterable<SuperHero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }

    @Override
    public List<SuperHero> getAllSuperheroesByName(String name) {
        return superheroRepository.getAllSuperheroesByName(name);
    }

    @Override
    public void deleteSuperHero(long superheroId) {
        superheroRepository.deleteById(superheroId);
    }

    @Override
    public SuperHero updateSuperhero(SuperHero superHero) {
        return superheroRepository.save(superHero);
    }
}
