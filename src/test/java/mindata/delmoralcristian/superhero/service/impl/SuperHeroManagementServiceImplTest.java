package mindata.delmoralcristian.superhero.service.impl;

import mindata.delmoralcristian.superhero.dao.impl.SuperHeroManagementDaoImpl;
import mindata.delmoralcristian.superhero.dto.SuperHeroRequest;
import mindata.delmoralcristian.superhero.exceptions.NotFoundException;
import mindata.delmoralcristian.superhero.model.SuperHero;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class SuperHeroManagementServiceImplTest {

    @Mock
    private SuperHeroManagementDaoImpl superHeroManagementDao;

    @InjectMocks
    private SuperHeroManagementServiceImpl superHeroManagementService ;

    private SuperHero superhero;

    private SuperHero superhero2;

    private SuperHero superhero3;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        superhero = SuperHero.builder()
                .name("Batman")
                .health(100)
                .death(Boolean.FALSE)
                .age(31)
                .power("Intellect, fighting skills, and wealth")
                .attack(60)
                .defense(20)
                .build();
        superhero2 = SuperHero.builder()
                .name("Superman")
                .health(100)
                .death(Boolean.FALSE)
                .age(35)
                .power("flight, superhuman strength, x-ray vision, heat vision, cold breath, super-speed, enhanced hearing, and nigh-invulnerability")
                .attack(90)
                .defense(30)
                .build();
        superhero3 = SuperHero.builder()
                .name("Spiderman")
                .health(100)
                .death(Boolean.FALSE)
                .age(24)
                .power("Superhuman strength, agility, endurance, ability to stick to and climb walls")
                .attack(70)
                .defense(25)
                .build();

    }

    @Test
    public void getSuperheroById() {

        Mockito.when(superHeroManagementDao.getSuperHero(anyLong())).thenReturn(Optional.of(superhero));

        SuperHero response = superHeroManagementService.getSuperHero(1L);

        Assert.assertEquals(response.getAge(), 31);
        Assert.assertEquals(response.getHealth(), 100);
    }

    @Test(expected = NotFoundException.class)
    public void getSuperheroByIdNotFound() {
        Mockito.when(superHeroManagementDao.getSuperHero(anyLong())).thenReturn(Optional.empty());
        superHeroManagementService.getSuperHero(1L);
    }

    @Test
    public void getAllSuperheroes() {
        Mockito.when(superHeroManagementDao.getAllSuperheroes()).thenReturn(Arrays.asList(superhero2, superhero3));

        List<SuperHero> response = superHeroManagementService.getAllSuperheroes();

        Assert.assertEquals(response.size(), 2);
        Assert.assertEquals(response.get(0).getHealth(), 100);
    }

    @Test
    public void getAllSuperheroesByName() {
        Mockito.when(superHeroManagementDao.getAllSuperheroesByName(anyString())).thenReturn(Arrays.asList(superhero2));

        List<SuperHero> response = superHeroManagementService.getAllSuperheroesByName("Spi");

        Assert.assertEquals(response.size(), 1);
        Assert.assertEquals(response.get(0).getHealth(), 100);
    }

    @Test
    public void updateSuperhero() {

        SuperHeroRequest superheroToUpdate = new SuperHeroRequest();
        superheroToUpdate.setHealth(107);
        superheroToUpdate.setAttack(79);

        Mockito.when(superHeroManagementDao.updateSuperhero(any())).thenReturn(superhero3);
        Mockito.when(superHeroManagementDao.getSuperHero(anyLong())).thenReturn(Optional.of(superhero3));

        SuperHero response = superHeroManagementService.updateSuperhero(1L, superheroToUpdate);

        Assert.assertNotNull(response);
    }

    @Test(expected = NotFoundException.class)
    public void updateSuperheroNotFound() {
        SuperHeroRequest superheroToUpdate = new SuperHeroRequest();
        superheroToUpdate.setHealth(107);
        superheroToUpdate.setAttack(79);

        Mockito.when(superHeroManagementDao.updateSuperhero(any())).thenReturn(superhero3);
        Mockito.when(superHeroManagementDao.getSuperHero(anyLong())).thenReturn(Optional.empty());

        superHeroManagementService.updateSuperhero(1L, superheroToUpdate);
    }

    @Test(expected = NotFoundException.class)
    public void deleteSuperheroNotFound() {
        Mockito.when(superHeroManagementDao.getSuperHero(anyLong())).thenReturn(Optional.empty());

        superHeroManagementService.deleteSuperHero(1L);
    }


}
