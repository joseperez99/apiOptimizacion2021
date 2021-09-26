package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import edu.unsj.fcefn.lcc.optimizacion.api.services.StopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frames")
public class FramesController {

    @Autowired
    FramesService framesService;

    @GetMapping(value = "")
    public List<FrameDTO> findAll()
    {
        return framesService.findAll();
    }

    @GetMapping(value = "{id}")
    public FrameDTO find (@PathVariable("id") Integer id)
    {
        return framesService.find(id);
    }

    @PostMapping(value = "")
    public FrameDTO add (@RequestBody FrameDTO frameDTO)
    {
        return framesService.add(frameDTO);
    }

    @PutMapping(value = "")
    public FrameDTO edit (@RequestBody FrameDTO frameDTO)
    {
        return framesService.edit(frameDTO);
    }

    @DeleteMapping(value = "{id}")
    public  FrameDTO delete (@PathVariable("id") Integer id ) throws Exception
    {
        return framesService.delete(id);
    }

}
