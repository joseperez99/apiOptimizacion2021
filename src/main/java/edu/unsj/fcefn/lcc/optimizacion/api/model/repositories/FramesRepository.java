package edu.unsj.fcefn.lcc.optimizacion.api.model.repositories;

import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.FrameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FramesRepository extends CrudRepository<FrameEntity, Integer> {

    List<FrameEntity> findAll();
}
