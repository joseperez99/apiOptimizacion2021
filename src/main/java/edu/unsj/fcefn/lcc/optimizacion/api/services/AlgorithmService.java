package edu.unsj.fcefn.lcc.optimizacion.api.services;

import edu.unsj.fcefn.lcc.optimizacion.api.algorithm.RoutingProblem;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.mappers.AlgorithmMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.variable.Permutation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.moeaframework.Executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;


@Service
public class AlgorithmService {

    @Autowired
    StopsService stopsService;

    @Autowired
    FramesService framesService;

    @Autowired
    AlgorithmMapper algorithmMapper;

    List<StopDTO> stops;
    List<FrameDTO> frames;

    public List<FrameDTO> execute(Integer[] ids)
    {
        stops = stopsService.getStopsById(ids);
        frames = framesService.findAll();

        // ------------------------ Console Print (: -------------------------- //
        System.out.println("Stops selected: ");
        stops.forEach(stopDTO -> System.out.println("> "+stopDTO.getName()));
        // -------------------------------------------------------------------- //

        if (ids.length < 2)                            { return new ArrayList<>(); }
        else if (ids.length == 2 || ids.length == 3)   { return algorithmMapper.permutationToFrameList(new Permutation(ids.length), stops); }
        else{
            NondominatedPopulation population = new Executor()
                    .withAlgorithm("NSGAII")
                    .withProblemClass(RoutingProblem.class, stops, frames, ids.length)
                    .withMaxEvaluations(10000)
                    .run();

            int[] head = {0};
            int[] feet = {ids.length-1};
            return StreamSupport
                    .stream(population.spliterator(),false)
                    .map(solution -> (Permutation) solution.getVariable(0))
                    .map(permutation -> algorithmMapper.permutationToFrameList(new Permutation(ArrayUtils.addAll(ArrayUtils.addAll(head, Arrays.stream(permutation.toArray()).map(i -> i+1).toArray()),feet)), stops))
                    .findFirst()
                    .orElse(new ArrayList<>());
        }
    }
}
