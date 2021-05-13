package edu.unsj.fcefn.lcc.optimizacion.api.services;

import edu.unsj.fcefn.lcc.optimizacion.api.algorithm.RoutingProblem;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import org.moeaframework.core.NondominatedPopulation;
import org.springframework.stereotype.Service;
import org.moeaframework.Executor;
import org.moeaframework.Analyzer;


import java.util.List;


@Service
public class AlgorithmService {

    public List<FrameDTO> execute()
    {
        NondominatedPopulation population = new Executor()
                .withAlgorithm("NSGAII")
                .withProblemClass(RoutingProblem.class)
                .withMaxEvaluations(100000)
                .run();

        Analyzer analyzer = new Analyzer()
                .withProblemClass(RoutingProblem.class)
                .includeHypervolume()
                .showStatisticalSignificance();

        analyzer.add("NSGAII",population);

        analyzer.printAnalysis();

        return null;
    }
}
