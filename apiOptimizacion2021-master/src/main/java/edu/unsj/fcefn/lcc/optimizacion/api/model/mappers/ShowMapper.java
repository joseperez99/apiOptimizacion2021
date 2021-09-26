package edu.unsj.fcefn.lcc.optimizacion.api.model.mappers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.ShowDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.StopsService;
import edu.unsj.fcefn.lcc.optimizacion.api.services.TransportCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {

    @Autowired
    TransportCompaniesService transportCompaniesService;

    @Autowired
    StopsService stopsService;

    public ShowDTO frameToShow(FrameDTO frameDTO)
    {
        ShowDTO showDTO = new ShowDTO();

        showDTO.setId(frameDTO.getId());
        showDTO.setNameTransportCompany(transportCompaniesService.find(frameDTO.getIdTransportCompany()).getName());
        showDTO.setNameStopDeparture(stopsService.find(frameDTO.getIdStopDeparture()).getName());
        showDTO.setNameStopArrival(stopsService.find(frameDTO.getIdStopArrival()).getName());
        showDTO.setPrice(frameDTO.getPrice());
        showDTO.setDepartureDateTime(frameDTO.getDepartureDateTime());
        showDTO.setArrivalDateTime(frameDTO.getArrivalDateTime());

        return showDTO;
    }
}
