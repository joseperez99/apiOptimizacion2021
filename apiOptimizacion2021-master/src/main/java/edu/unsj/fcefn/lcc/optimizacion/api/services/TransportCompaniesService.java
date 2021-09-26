package edu.unsj.fcefn.lcc.optimizacion.api.services;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.TransportCompanyDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.TransportCompanyEntity;
import edu.unsj.fcefn.lcc.optimizacion.api.model.mappers.TransportCompanyMapper;
import edu.unsj.fcefn.lcc.optimizacion.api.model.repositories.TransportCompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportCompaniesService {

    @Autowired
    TransportCompaniesRepository transportCompaniesRepository;

    @Autowired
    TransportCompanyMapper transportCompanyMapper;

    public List<TransportCompanyDTO> findAll()
    {
        return transportCompaniesRepository
                .findAll()
                .stream()
                .map(transportCompanyEntity -> transportCompanyMapper.entityToDTO(transportCompanyEntity))
                .collect(Collectors.toList());
    }

    public TransportCompanyDTO find(Integer id)
    {
        return transportCompaniesRepository
                .findById(id)
                .map(transportCompanyEntity -> transportCompanyMapper.entityToDTO(transportCompanyEntity))
                .orElse(null);
    }

    public TransportCompanyDTO add(TransportCompanyDTO transportCompanyDTO)
    {
        TransportCompanyEntity transportCompanyEntity = transportCompanyMapper.dtoToEntity(transportCompanyDTO);
        transportCompanyEntity = transportCompaniesRepository.save(transportCompanyEntity);
        return transportCompanyMapper.entityToDTO(transportCompanyEntity);
    }

    public TransportCompanyDTO edit(TransportCompanyDTO transportCompanyDTO)
    {
        TransportCompanyEntity transportCompanyEntity = transportCompanyMapper.dtoToEntity(transportCompanyDTO);
        transportCompanyEntity = transportCompaniesRepository.save(transportCompanyEntity);
        return transportCompanyMapper.entityToDTO(transportCompanyEntity);
    }

    public TransportCompanyDTO delete(Integer id) throws Exception
    {
        Optional<TransportCompanyEntity> transportCompanyEntityOptional = transportCompaniesRepository.findById(id);
        if(transportCompanyEntityOptional.isPresent())
        {
            transportCompaniesRepository.deleteById(id);
            return transportCompanyMapper.entityToDTO(transportCompanyEntityOptional.get());
        }
        else
        {
            throw new Exception("TransportCompany not found");
        }
    }

}
