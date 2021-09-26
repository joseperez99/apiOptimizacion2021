package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.ShowDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.mappers.ShowMapper;
import edu.unsj.fcefn.lcc.optimizacion.api.services.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("algorithm")
public class AlgorithmController {

    @Autowired
    AlgorithmService algorithmService;

    @Autowired
    ShowMapper showMapper;

    List<FrameDTO> frames;

    @GetMapping(value = "run")
    public List<ShowDTO> run()
    {
        frames = algorithmService.execute();

        List<ShowDTO> showDTOS = new ArrayList<>();

        for (FrameDTO f: frames)
        {
            showDTOS.add(showMapper.frameToShow(f));
        }

        return showDTOS;
    }
}
