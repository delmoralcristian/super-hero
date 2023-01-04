package mindata.delmoralcristian.superhero.service.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import mindata.delmoralcristian.superhero.advice.TrackProcessingTime;
import mindata.delmoralcristian.superhero.dao.SuperHeroManagementDao;
import mindata.delmoralcristian.superhero.dto.SuperHeroRequest;
import mindata.delmoralcristian.superhero.enums.CommonMessage;
import mindata.delmoralcristian.superhero.exceptions.NotFoundException;
import mindata.delmoralcristian.superhero.model.SuperHero;
import mindata.delmoralcristian.superhero.service.SuperHeroManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SuperHeroManagementServiceImpl implements SuperHeroManagementService {

    @Autowired
    private SuperHeroManagementDao superHeroManagementDao;

    @Override
    public SuperHero getSuperHero(long superheroId) throws NotFoundException {
        var logMessage = new StringBuilder("Getting all superheroes by id: ").append(superheroId);
        log.info(logMessage.toString());
        return this.getSuperHeroById(superheroId);
    }

    @Override
    @TrackProcessingTime
    public List<SuperHero> getAllSuperheroes() {
        log.info("Getting all superheroes");
        return (List<SuperHero>) superHeroManagementDao.getAllSuperheroes();
    }

    @Override
    @TrackProcessingTime
    public List<SuperHero> getAllSuperheroesByName(String name) {
        var logMessage = new StringBuilder("Getting all superheroes by name: ").append(name);
        log.info(logMessage.toString());
        return superHeroManagementDao.getAllSuperheroesByName(name);
    }

    @Override
    @TrackProcessingTime
    public void deleteSuperHero(long superheroId) throws NotFoundException {
        var superHero = this.getSuperHeroById(superheroId);
        var logMessage = new StringBuilder("Deleting superhero - SuperheroId ").append(superheroId);
        log.info(logMessage.toString());
        superHeroManagementDao.deleteSuperHero(superHero.getId());
    }

    @Override
    @TrackProcessingTime
    public SuperHero updateSuperhero(long superheroId, SuperHeroRequest superHeroRequest) throws NotFoundException {
        this.getSuperHeroById(superheroId);
        var logMessage = new StringBuilder("Updating superhero - SuperheroId ").append(superheroId);
        log.info(logMessage.toString());
        return superHeroManagementDao.updateSuperhero(SuperHeroRequest.of(superheroId, superHeroRequest));
    }

    private SuperHero getSuperHeroById(long superheroId) {
        var superHero = superHeroManagementDao.getSuperHero(superheroId);
        if(!superHero.isPresent()) {
            throw new NotFoundException(String.format(CommonMessage.SUPER_HERO_NOT_FOUND.getMessage(), superheroId));
        }
        return superHero.get();
    }

}
