package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalTime;

@RestController()
@RequestMapping("/test")
public class TestController {

    @Autowired
    FramesService framesService;

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
}
