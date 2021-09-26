package edu.unsj.fcefn.lcc.optimizacion.api.model.mappers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.StopEntity;
import org.springframework.stereotype.Component;

@Component
public class StopMapper {

    public StopDTO entityToDTO (StopEntity stopEntity)
    {
        StopDTO stopDTO = new StopDTO();

        stopDTO.setId(stopEntity.getId());
        stopDTO.setName(stopEntity.getName());
        stopDTO.setCity(stopEntity.getCity());
        stopDTO.setProvince(stopEntity.getProvince());
        stopDTO.setCountry(stopEntity.getCountry());
        stopDTO.setRanking(stopEntity.getRanking());

        return stopDTO;
    }

    public StopEntity dtoToEntity (StopDTO stopDTO)
    {
        StopEntity stopEntity = new StopEntity();

        stopEntity.setId(stopDTO.getId());
        stopEntity.setName(stopDTO.getName());
        stopEntity.setCity(stopDTO.getCity());
        stopEntity.setProvince(stopDTO.getProvince());
        stopEntity.setCountry(stopDTO.getCountry());
        stopEntity.setRanking(stopDTO.getRanking());

        return stopEntity;
    }

}
