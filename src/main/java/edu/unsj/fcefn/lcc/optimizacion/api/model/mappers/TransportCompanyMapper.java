package edu.unsj.fcefn.lcc.optimizacion.api.model.mappers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.TransportCompanyDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.TransportCompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class TransportCompanyMapper
{
    public TransportCompanyDTO entityToDTO(TransportCompanyEntity transportCompanyEntity)
    {
        TransportCompanyDTO transportCompanyDTO = new TransportCompanyDTO();

        transportCompanyDTO.setId(transportCompanyEntity.getId());
        transportCompanyDTO.setLogo(transportCompanyEntity.getLogo());
        transportCompanyDTO.setName(transportCompanyEntity.getName());

        return transportCompanyDTO;
    }

    public TransportCompanyEntity dtoToEntity(TransportCompanyDTO transportCompanyDTO){
        TransportCompanyEntity transportCompanyEntity = new TransportCompanyEntity();

        transportCompanyEntity.setId(transportCompanyDTO.getId());
        transportCompanyEntity.setLogo(transportCompanyDTO.getLogo());
        transportCompanyEntity.setName(transportCompanyDTO.getName());

        return transportCompanyEntity;
    }
}
