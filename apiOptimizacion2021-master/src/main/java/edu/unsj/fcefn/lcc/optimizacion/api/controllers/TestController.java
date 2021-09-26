package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import edu.unsj.fcefn.lcc.optimizacion.api.services.StopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/test")
public class TestController {

    @Autowired
    FramesService framesService;

    @Autowired
    StopsService stopsService;

    @GetMapping(value = "{id}")
    public Long test (@PathVariable("id") Integer id)
    {
        FrameDTO frame = framesService.find(id);
        if (frame.getDepartureDateTime().isBefore(frame.getArrivalDateTime()))
        {
            return Duration.between(frame.getDepartureDateTime(),frame.getArrivalDateTime()).toMinutes();
        }
        else
        {
            return 1440 - Duration.between(frame.getArrivalDateTime(),frame.getDepartureDateTime()).toMinutes();
        }
    }

    @GetMapping(value = "")
    public List<StopDTO> test1 ()
    {
        return stopsService
                .findAll()
                .stream()
                .sorted(Comparator.comparing(StopDTO::getRanking).reversed())
                .collect(Collectors.toList())
                .subList(0,3);
    }
/*
    @GetMapping(value = "a")
    public List<FrameDTO> test2 ()
    {
        return framesService
                .findByIdDeparturesStopAndIdArrivalStop(10, 19);
    }

    @GetMapping(value = "b")
    public List<FrameDTO> test3 ()
    {
        return framesService
                .findByIdDeparturesStopAndIdArrivalStop(19, 315);
    }
 */
}
