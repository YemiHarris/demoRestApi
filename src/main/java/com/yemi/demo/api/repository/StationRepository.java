package com.yemi.demo.api.repository;

import com.yemi.demo.api.model.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {

    Station findByName(String name);

    List<Station> findByHdEnabled(boolean hdEnabled);

}
