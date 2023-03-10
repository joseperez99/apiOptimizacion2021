package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("algorithm")
public class AlgorithmController {

    @Autowired
    AlgorithmService algorithmService;

    @GetMapping(value = "run")
    public List<FrameDTO> run(@RequestParam Integer[] ids)
    {
        // ------------------------ Console Print (: -------------------------- //
        System.out.println("Running with: ");
        System.out.print("<StopsID> -> ");
        System.out.println(Arrays.toString(ids));
        // -------------------------------------------------------------------- //

        return algorithmService.execute(ids);
    }
}
