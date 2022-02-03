package monprojet.dao;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import monprojet.entity.Country;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {


/**
* Calcule la population d'un pays
* @param  Id est passé en paramètre
* @return somme des populations des villes
*/
@Query(value="SELECT SUM(City.population) AS populationTotale "
    + "FROM City "
    + "INNER JOIN Country ON City.country_id = Country.id "
    + "WHERE Country.id = :Id",
    nativeQuery = true)
public int PopulationPays(Integer Id);

/**
* Renvoie une liste de nom de pays et sa population
* @return une liste de nom de pays et sa population
*/
@Query(value="SELECT Country.name, SUM(City.population) AS populationTotale "
    + "FROM City "
    + "INNER JOIN Country ON City.country_id = Country.id "
    + "GROUP BY Country.name",
    nativeQuery = true)
public Map<String, Integer> listPays();
}


        