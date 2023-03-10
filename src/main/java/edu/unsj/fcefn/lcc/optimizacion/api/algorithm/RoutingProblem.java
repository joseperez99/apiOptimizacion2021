package edu.unsj.fcefn.lcc.optimizacion.api.algorithm;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.Permutation;
import org.moeaframework.problem.AbstractProblem;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class RoutingProblem extends AbstractProblem {

    List<StopDTO> stops;
    List<FrameDTO> frames;

    Integer nIds;

    public RoutingProblem(List<StopDTO> stops, List<FrameDTO> frames, Integer nIds)
    {
        super(1,2);
        this.nIds = nIds;
        this.stops = stops;
        this.frames = frames;
    }

    @Override
    public void evaluate(Solution solution)
    {
        solution.setObjective(0, totalPrice(solution.getVariable(0)));
        solution.setObjective(1, totalTime(solution.getVariable(0)));
    }

    private double totalPrice(Variable variable)
    {
        Permutation permutation = (Permutation) variable;
        double totalPrice = 0;

        StopDTO departureStop;
        StopDTO arrivalStop;

        departureStop = stops.get(0);
        for (int i = 0; i < permutation.size() ; i++)
        {
            arrivalStop = stops.get(permutation.get(i)+1);

            List<FrameDTO> framesAux = findByIdDeparturesStopAndIdArrivalStop(departureStop.getId(), arrivalStop.getId());

            FrameDTO bestPrice = framesAux
                    .stream()
                    .min(Comparator.comparing(FrameDTO::getPrice))
                    .orElse(null);

            if (Objects.isNull(bestPrice)){ return Double.MAX_VALUE; }

            totalPrice += bestPrice.getPrice();
            departureStop = arrivalStop;
        }

        arrivalStop = stops.get(nIds-1);

        List<FrameDTO> framesAux = findByIdDeparturesStopAndIdArrivalStop(departureStop.getId(), arrivalStop.getId());

        FrameDTO bestPrice = framesAux
                .stream()
                .min(Comparator.comparing(FrameDTO::getPrice))
                .orElse(null);

        if (Objects.isNull(bestPrice)) { return Double.MAX_VALUE; }

        totalPrice += bestPrice.getPrice();
        return totalPrice;
    }

    private double totalTime(Variable variable)
    {
        Permutation permutation = (Permutation) variable;
        double totalTime = 0;

        FrameDTO frameDTO = null;
        StopDTO departureStop;
        StopDTO arrivalStop;

        departureStop = stops.get(0);
        for (int i = 0; i < permutation.size(); i++)
        {
            arrivalStop = stops.get(permutation.get(i)+1);
            Map<Integer, Long> mapTime;

            List<FrameDTO> framesAux = findByIdDeparturesStopAndIdArrivalStop(departureStop.getId(), arrivalStop.getId());

            if (framesAux.isEmpty()){ return Double.MAX_VALUE; }

            if (i==0){ mapTime = getMapTime1(framesAux); }
            else{ mapTime = getMapTime(framesAux, frameDTO.getArrivalDateTime()); }

            Map.Entry<Integer, Long> frameIdDuration = mapTime
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .orElse(null);

            frameDTO = framesAux
                    .stream()
                    .filter(frame -> frame.getId().equals(Objects.requireNonNull(frameIdDuration).getKey()))
                    .findFirst()
                    .orElse(null);

            if(Objects.isNull(frameDTO)){ return Double.MAX_VALUE; }

            totalTime += frameIdDuration.getValue();
            departureStop = arrivalStop;
        }

        arrivalStop = stops.get(nIds-1);
        Map<Integer, Long> mapTime;

        List<FrameDTO> framesAux = findByIdDeparturesStopAndIdArrivalStop(departureStop.getId(), arrivalStop.getId());

        if (framesAux.isEmpty()){ return Double.MAX_VALUE; }

        if (Objects.isNull(frameDTO)){ mapTime = getMapTime1(framesAux); }
        else{ mapTime = getMapTime(framesAux, frameDTO.getArrivalDateTime()); }

        Map.Entry<Integer, Long> frameIdDuration = mapTime
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);

        frameDTO = framesAux
                .stream()
                .filter(frame -> frame.getId().equals(Objects.requireNonNull(frameIdDuration).getKey()))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(frameDTO)){ return Double.MAX_VALUE; }

        totalTime += frameIdDuration.getValue();
        return totalTime;
    }

    private Map<Integer,Long> getMapTime1(List<FrameDTO> frames)
    {
        Map<Integer,Long> timeMap = new HashMap<>();

        for(FrameDTO frame : frames)
        {
            if (frame.getDepartureDateTime().isBefore(frame.getArrivalDateTime()))
            {
                timeMap.put(frame.getId(), Duration.between(frame.getDepartureDateTime(),frame.getArrivalDateTime()).toMinutes());
            }
            else
            {
                timeMap.put(frame.getId(),1440 - Duration.between(frame.getArrivalDateTime(), frame.getDepartureDateTime()).toMinutes());
            }
        }
        return timeMap;
    }

    private Map<Integer,Long> getMapTime(List<FrameDTO> frames, LocalTime previousArrivalTime)
    {
        Map<Integer,Long> timeMap = new HashMap<>();
        Long tripDuration;
        Long waitDuration;

        for(FrameDTO frame : frames)
        {
            if (frame.getDepartureDateTime().isBefore(frame.getArrivalDateTime()))
            {
                tripDuration = Duration.between(frame.getDepartureDateTime(),frame.getArrivalDateTime()).toMinutes();

            }
            else
            {
                tripDuration = 1440 - Duration.between(frame.getArrivalDateTime(),frame.getDepartureDateTime()).toMinutes();

            }
            if (previousArrivalTime.isBefore(frame.getDepartureDateTime()))
            {
                waitDuration = Duration.between(previousArrivalTime, frame.getDepartureDateTime()).toMinutes();
            }
            else
            {
                waitDuration = 1440 - Duration.between(frame.getDepartureDateTime(), previousArrivalTime).toMinutes();
            }

            timeMap.put(frame.getId(),tripDuration+waitDuration);

        }
        return timeMap;
    }

    public List<FrameDTO> findByIdDeparturesStopAndIdArrivalStop(Integer idDepartureStop, Integer idArrivalStop)
    {
        return  this.frames
                .stream()
                .filter(frameDTO -> frameDTO.getIdStopDeparture().equals(idDepartureStop))
                .filter(frameDTO -> frameDTO.getIdStopArrival().equals(idArrivalStop))
                .collect(Collectors.toList());
    }

    @Override
    public Solution newSolution()
    {
        Solution solution = new Solution(1,2);
        solution.setVariable(0, EncodingUtils.newPermutation(nIds-2));
        return solution;
    }
}
