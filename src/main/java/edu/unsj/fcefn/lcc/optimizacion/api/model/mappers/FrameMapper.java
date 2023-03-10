package edu.unsj.fcefn.lcc.optimizacion.api.model.mappers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.FrameEntity;
import org.springframework.stereotype.Component;

@Component
public class FrameMapper {

    public FrameDTO entityToDTO(FrameEntity frameEntity)
    {
        FrameDTO frameDTO = new FrameDTO();

        frameDTO.setId(frameEntity.getId());
        frameDTO.setIdTransportCompany(frameEntity.getIdTrasportCompany());
        frameDTO.setIdStopDeparture(frameEntity.getIdStopDeparture());
        frameDTO.setIdStopArrival(frameEntity.getIdStopArrival());
        frameDTO.setPrice(frameEntity.getPrice());
        frameDTO.setCategory(frameEntity.getCategory());
        frameDTO.setDepartureDateTime(frameEntity.getDepartureDateTime());
        frameDTO.setArrivalDateTime(frameEntity.getArrivalDateTime());

        return frameDTO;
    }

    public FrameEntity dtoToEntity (FrameDTO frameDTO)
    {
        FrameEntity frameEntity = new FrameEntity();

        frameEntity.setId(frameDTO.getId());
        frameEntity.setIdTrasportCompany(frameDTO.getIdTransportCompany());
        frameEntity.setIdStopDeparture(frameDTO.getIdStopDeparture());
        frameEntity.setIdStopArrival(frameDTO.getIdStopArrival());
        frameEntity.setPrice(frameDTO.getPrice());
        frameEntity.setCategory(frameDTO.getCategory());
        frameEntity.setDepartureDateTime(frameDTO.getDepartureDateTime());
        frameEntity.setArrivalDateTime(frameDTO.getArrivalDateTime());

        return frameEntity;
    }
}
