package edu.unsj.fcefn.lcc.optimizacion.api.model.repositories;

import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.StopEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopsRepository extends CrudRepository<StopEntity, Integer> {

    List<StopEntity> findAll();

}
