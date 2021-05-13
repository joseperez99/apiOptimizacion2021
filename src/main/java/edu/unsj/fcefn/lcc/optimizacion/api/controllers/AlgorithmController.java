package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.AlgorithmService;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("algorithm")
public class AlgorithmController {

    @Autowired
    AlgorithmService algorithmService;

    @Autowired
    FramesService framesService;

    @GetMapping(value = "run")
    public List<FrameDTO> run()
    {
        return algorithmService.execute();
    }

}
