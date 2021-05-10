package edu.unsj.fcefn.lcc.optimizacion.api.model.repositories;

import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.TransportCompanyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportCompaniesRepository extends CrudRepository<TransportCompanyEntity, Integer> {

   List<TransportCompanyEntity> findAll();

}

