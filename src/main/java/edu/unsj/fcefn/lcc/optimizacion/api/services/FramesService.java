package edu.unsj.fcefn.lcc.optimizacion.api.services;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.FrameEntity;
import edu.unsj.fcefn.lcc.optimizacion.api.model.mappers.FrameMapper;
import edu.unsj.fcefn.lcc.optimizacion.api.model.repositories.FramesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FramesService {

    @Autowired
    FramesRepository framesRepository;

    @Autowired
    FrameMapper frameMapper;

    List<FrameDTO> frames;

    @PostConstruct
    private void init()
    {
        this.frames = this.findAll();
    }

    public List<FrameDTO> findAll()
    {
        return framesRepository
                .findAll()
                .stream()
                .map(stopEntity -> frameMapper.entityToDTO(stopEntity))
                .collect(Collectors.toList());
    }

    public FrameDTO find(Integer id)
    {
        return framesRepository
                .findById(id)
                .map(frameEntity -> frameMapper.entityToDTO(frameEntity))
                .orElse(null);
    }

    public FrameDTO add(FrameDTO frameDTO)
    {
        FrameEntity frameEntity = frameMapper.dtoToEntity(frameDTO);
        framesRepository.save(frameEntity);
        return frameMapper.entityToDTO(frameEntity);
    }

    public FrameDTO edit(FrameDTO frameDTO)
    {
        FrameEntity frameEntity = frameMapper.dtoToEntity(frameDTO);
        framesRepository.save(frameEntity);
        return frameMapper.entityToDTO(frameEntity);
    }

    public FrameDTO delete(Integer id) throws Exception
    {
        Optional<FrameEntity> frameEntityOptional = framesRepository.findById(id);
        if(frameEntityOptional.isPresent())
        {
            framesRepository.deleteById(id);
            return frameMapper.entityToDTO((frameEntityOptional.get()));
        }
        else
        {
            throw new Exception("Frame not found");
        }
    }

    public List<FrameDTO> findByIdDeparturesStopAndIdArrivalStop(Integer idDepartureStop, Integer idArrivalStop)
    {
        return this.frames
                .stream()
                .filter(frameDTO -> frameDTO.getIdStopDeparture().equals(idDepartureStop))
                .filter(frameDTO -> frameDTO.getArrivalDateTime().equals(idArrivalStop))
                .collect(Collectors.toList());
    }

}
