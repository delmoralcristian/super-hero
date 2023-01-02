package mindata.delmoralcristian.superhero.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mindata.delmoralcristian.superhero.dto.SuperHeroRequest;
import mindata.delmoralcristian.superhero.dto.SuperHeroResponse;
import mindata.delmoralcristian.superhero.service.SuperHeroManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Superhero Microservice")
public class SuperHeroManagementController {

    @Autowired
    private SuperHeroManagementService superHeroManagementService;

    @RequestMapping(value = "/superhero", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get all superheros")
    public ResponseEntity<List<SuperHeroResponse>> getAllSuperHeroes() {
        return ResponseEntity.ok(SuperHeroResponse.of(superHeroManagementService.getAllSuperheroes()));
    }

    @RequestMapping(value = "/superhero/id/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get superhero by id")
    public ResponseEntity<SuperHeroResponse> getSuperHeroById(@PathVariable Long id) {
        return ResponseEntity.ok(SuperHeroResponse.of(superHeroManagementService.getSuperHero(id)));
    }

    @RequestMapping(value = "/superhero/name/{name}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get all superheros by name")
    public ResponseEntity<List<SuperHeroResponse>> getAllSuperHeroesByName(@PathVariable String name) {
        return ResponseEntity.ok(SuperHeroResponse.of(superHeroManagementService.getAllSuperheroesByName(name)));
    }

    @RequestMapping(value = "/superhero/id/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Update superhero")
    public ResponseEntity<SuperHeroResponse> updateSuperHero(@PathVariable Long id,
                                          @RequestBody SuperHeroRequest superHeroRequest) {
        return ResponseEntity.ok(SuperHeroResponse.of(superHeroManagementService.updateSuperhero(id, superHeroRequest)));
    }

    @RequestMapping(value = "/superhero/id/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete superhero")
    public ResponseEntity deleteSuperHero(@PathVariable Long id) {
        superHeroManagementService.deleteSuperHero(id);
        return ResponseEntity.accepted().build();
    }
}
