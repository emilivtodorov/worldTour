package com.example.europetour.repository;

import com.example.europetour.model.entity.Country;
import com.example.europetour.model.entity.Neighbours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighboursRepository extends JpaRepository<Neighbours, Long> {

    List<Neighbours> findAllByCountry_Id(long country_id);
}
