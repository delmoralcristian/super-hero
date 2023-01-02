package mindata.delmoralcristian.superhero.service.impl;

import mindata.delmoralcristian.superhero.dao.SuperHeroManagementDao;
import mindata.delmoralcristian.superhero.dto.SuperHeroRequest;
import mindata.delmoralcristian.superhero.enums.CommonMessage;
import mindata.delmoralcristian.superhero.exceptions.NotFoundException;
import mindata.delmoralcristian.superhero.model.SuperHero;
import mindata.delmoralcristian.superhero.service.SuperHeroManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroManagementServiceImpl implements SuperHeroManagementService {

    @Autowired
    private SuperHeroManagementDao superHeroManagementDao;

    @Override
    public SuperHero getSuperHero(long superheroId) throws NotFoundException {
        return this.getSuperHeroById(superheroId);
    }

    @Override
    public List<SuperHero> getAllSuperheroes() {
        return (List<SuperHero>) superHeroManagementDao.getAllSuperheroes();
    }

    @Override
    public List<SuperHero> getAllSuperheroesByName(String name) {
        return superHeroManagementDao.getAllSuperheroesByName(name);
    }

    @Override
    public void deleteSuperHero(long superheroId) throws NotFoundException {
        SuperHero superHero = this.getSuperHeroById(superheroId);
        superHeroManagementDao.deleteSuperHero(superHero.getId());
    }

    @Override
    public SuperHero updateSuperhero(long superheroId, SuperHeroRequest superHeroRequest) throws NotFoundException {
        this.getSuperHeroById(superheroId);
        return superHeroManagementDao.updateSuperhero(SuperHeroRequest.of(superheroId, superHeroRequest));
    }

    private SuperHero getSuperHeroById(long superheroId) {
        Optional<SuperHero> superHero = superHeroManagementDao.getSuperHero(superheroId);
        if(!superHero.isPresent()) {
            throw new NotFoundException(String.format(CommonMessage.SUPER_HERO_NOT_FOUND.getMessage(), superheroId));
        }
        return superHero.get();
    }

}
