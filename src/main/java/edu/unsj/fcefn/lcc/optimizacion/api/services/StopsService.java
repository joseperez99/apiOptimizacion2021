package edu.unsj.fcefn.lcc.optimizacion.api.services;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.StopEntity;
import edu.unsj.fcefn.lcc.optimizacion.api.model.mappers.StopMapper;
import edu.unsj.fcefn.lcc.optimizacion.api.model.repositories.StopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StopsService
{
    @Autowired
    StopsRepository stopsRepository;

    @Autowired
    StopMapper stopMapper;

    public List<StopDTO> findAll()
    {
        return stopsRepository
                .findAll()
                .stream()
                .map(stopEntity -> stopMapper.entityToDTO(stopEntity))
                .collect(Collectors.toList());
    }

    public StopDTO find(Integer id)
    {
        return stopsRepository
                .findById(id)
                .map(stopEntity -> stopMapper.entityToDTO(stopEntity))
                .orElse(null);
    }

    public StopDTO add(StopDTO stopDTO)
    {
        StopEntity stopEntity = stopMapper.dtoToEntity(stopDTO);
        stopEntity = stopsRepository.save(stopEntity);
        return stopMapper.entityToDTO(stopEntity);
    }

    public StopDTO edit(StopDTO stopDTO)
    {
        StopEntity stopEntity = stopMapper.dtoToEntity(stopDTO);
        stopEntity = stopsRepository.save(stopEntity);
        return stopMapper.entityToDTO(stopEntity);
    }

    public StopDTO delete(Integer id) throws Exception
    {
        Optional<StopEntity> stopEntityOptional = stopsRepository.findById(id);
        if(stopEntityOptional.isPresent())
        {
            stopsRepository.deleteById(id);
            return stopMapper.entityToDTO((stopEntityOptional.get()));
        }
        else
        {
            throw new Exception("Stop not found");
        }
    }

}
