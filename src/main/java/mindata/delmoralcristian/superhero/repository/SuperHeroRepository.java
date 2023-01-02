package mindata.delmoralcristian.superhero.repository;

import java.util.List;

import mindata.delmoralcristian.superhero.model.SuperHero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {

    @Query(value = "SELECT * FROM SuperHero AS sh WHERE sh.name LIKE %:name%", nativeQuery = true)
    List<SuperHero> getAllSuperheroesByName(String name);
}
