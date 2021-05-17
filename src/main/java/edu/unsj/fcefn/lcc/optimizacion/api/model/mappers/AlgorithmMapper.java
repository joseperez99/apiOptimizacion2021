package edu.unsj.fcefn.lcc.optimizacion.api.model.mappers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import org.moeaframework.core.variable.Permutation;
import org.moeaframework.problem.misc.Lis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Component
public class AlgorithmMapper {

    @Autowired
    FramesService framesService;

    public List<FrameDTO> permutationToFrameList (Permutation permutation, List<StopDTO> stops)
    {
        List<FrameDTO> solutionFrames = new ArrayList<>();
        FrameDTO frameDTO = new FrameDTO();

        for (int i = 0; i < permutation.size() - 1; i++)
        {
            StopDTO departureStop = stops.get(permutation.get(i));
            StopDTO arrivalStop = stops.get(permutation.get(i+1));
            Map<Integer, Long> mapTime;

            List<FrameDTO> frames = framesService
                    .findByIdDeparturesStopAndIdArrivalStop(departureStop.getId(), arrivalStop.getId());

            if (i==0)
            {
                mapTime = getMapTime1(frames);
            }
            else
            {
                mapTime = getMapTime(frames, frameDTO.getArrivalDateTime());
            }

            Map.Entry<Integer, Long> frameIdDuration = mapTime
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .orElse(null);

            frameDTO = frames
                    .stream()
                    .filter(frame -> frame.getId().equals(Objects.requireNonNull(frameIdDuration).getKey()))
                    .findFirst()
                    .orElse(null);

            if(Objects.isNull(frameDTO))
            {
                return new ArrayList<>();
            }

            solutionFrames.add(frameDTO);

        }

        return solutionFrames;
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

}
