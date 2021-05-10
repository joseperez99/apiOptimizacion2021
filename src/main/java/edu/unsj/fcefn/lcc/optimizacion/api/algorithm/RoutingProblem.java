package edu.unsj.fcefn.lcc.optimizacion.api.algorithm;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import edu.unsj.fcefn.lcc.optimizacion.api.services.StopsService;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.Permutation;
import org.moeaframework.problem.AbstractProblem;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class RoutingProblem extends AbstractProblem {

    @Autowired
    private StopsService stopsService;

    @Autowired
    FramesService framesService;

    List<StopDTO> stops;

    public RoutingProblem()
    {
        super(1, 2);
        stops = stopsService
                .findAll()
                .stream()
                .sorted(Comparator.comparing(StopDTO::getRanking).reversed())
                .collect(Collectors.toList())
                .subList(0,20);
    }

    @Override
    public void evaluate(Solution solution)
    {
        solution.setObjective(0, totalPrice(solution.getVariable(0)));
        solution.setObjective(1, totalTime(solution.getVariable(1)));
    }

    private double totalPrice(Variable variable)
    {
        Permutation permutation = (Permutation) variable;

        double totalPrice = 0;

        for (int i = 0; i < permutation.size() - 1; i++)
        {
            StopDTO departureStop = stops.get(permutation.get(i));
            StopDTO arrivalStop = stops.get(permutation.get(i));

            List<FrameDTO> frames = framesService
                    .findByIdDeparturesStopAndIdArrivalStop(departureStop.getId(), arrivalStop.getId());

            FrameDTO bestPrice = frames
                    .stream()
                    .min(Comparator.comparing(FrameDTO::getPrice))
                    .orElse(null);

            if (Objects.isNull(bestPrice))
            {
                return Double.MAX_VALUE;
            }

            totalPrice += bestPrice.getPrice();
        }

        return totalPrice;
    }

    private double totalTime(Variable variable)
    {
        Permutation permutation = (Permutation) variable;

        double totalTime = 0;

        for (int i = 0; i < permutation.size() - 1; i++)
        {
            StopDTO departureStop = stops.get(permutation.get(i));
            StopDTO arrivalStop = stops.get(permutation.get(i));

            List<FrameDTO> frames = framesService
                    .findByIdDeparturesStopAndIdArrivalStop(departureStop.getId(), arrivalStop.getId());

            Map<Integer,Long> mapTime = getTimeMaps(frames);

            Map.Entry<Integer, Long> frameIdTimeToArrival = mapTime
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .orElse(null);
        }

        return totalTime;
    }

    private Map<Integer,Long> getTimeMaps(List<FrameDTO> frames)
    {
        Map<Integer,Long> timeMap = new HashMap<>();
        for(FrameDTO frame : frames)
        {
            if (frame.getDepartureDateTime().isBefore(frame.getArrivalDateTime()))
            {
                timeMap.put(frame.getId(),Duration.between(frame.getDepartureDateTime(),frame.getArrivalDateTime()).toMinutes());
            }
            else
            {
                timeMap.put(frame.getId(),1440 - Duration.between(frame.getArrivalDateTime(),frame.getDepartureDateTime()).toMinutes());
            }
        }
        return timeMap;
    }

    @Override
    public Solution newSolution()
    {
        Solution solution = new Solution(1,2);
        solution.setVariable(0, EncodingUtils.newPermutation(20));
        return solution;
    }
}
