package com.example.europetour.repository;

import com.example.europetour.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findCountryByCode(String code);

    List<Country> findAllByCodeIn(Collection<String> code);

    Country findCountryByName(String name);
}
