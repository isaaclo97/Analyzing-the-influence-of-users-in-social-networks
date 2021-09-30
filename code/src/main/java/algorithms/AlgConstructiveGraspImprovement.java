package algorithms;

import grafo.optilib.metaheuristics.Algorithm;
import grafo.optilib.metaheuristics.Constructive;
import grafo.optilib.results.Result;
import grafo.optilib.structure.Solution;
import localSearch.SurrogateRandom;
import structure.SNIInstance;
import structure.SNISolution;

import java.util.concurrent.TimeUnit;

public class AlgConstructiveGraspImprovement implements Algorithm<SNIInstance> {

	final Constructive<SNIInstance, SNISolution> constructive;
	private Integer iterations;
	private String name;
	private SurrogateRandom improvement;

	public AlgConstructiveGraspImprovement(Constructive<SNIInstance, SNISolution> constructive, Integer iterations, String name, SurrogateRandom improvement){
		this.constructive = constructive;
		this.iterations = iterations;
		this.name = name;
		this.improvement = improvement;
	}

	@Override
	public Result execute(SNIInstance sniInstance){
		Result r = new Result(sniInstance.getName() + '-' + name);
		final long startTime = System.nanoTime();
		System.out.println("Nodes: " + sniInstance.getNumNodes());
		double sol=0;
		SNISolution res = null;
		for(int i=0; i<iterations;i++) {
			System.out.println("Iteration "+i+ " out of "+iterations);
			SNISolution sniSolution = constructive.constructSolution(sniInstance);
			if(res==null){
				res = new SNISolution(sniInstance,sniSolution.getSelectedNodes(),sniSolution.getK(),sniSolution.getMc(),sniSolution.getP());
			}
			double resMark = sniSolution.getMark();
			if(resMark>sol){
				sol = resMark;
				res.copy(sniSolution);
			}
		}
		long timeToSolution = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
		double seconds = timeToSolution / 1000.0;
		r.add("Time Constructive (s)", seconds);
		r.add("Constructive value", sol);

		System.out.println("Time Constructive (s): " + seconds);
		System.out.println("Constructive value: " + sol);

		improvement.improve(res);

		System.out.println("MAIN: " + res.getMark());
		System.out.println("Nodes selected");
		for(Integer node:res.getSelectedNodes()){
			System.out.print(node+" ");
		}
		System.out.println();
		System.out.println("Value " + res.getMark());

		timeToSolution = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

		// Evaluate the solution quality
		seconds = timeToSolution / 1000.0;
		System.out.println("Time (s): " + seconds);

		r.add("Time (s)", seconds);
		r.add("# Global Spread Value", res.getSpreadValue());
		r.add("# Active Nodes", res.getActiveNodes());
        r.add("# K", res.getK());
        r.add("# Montecarlo", res.getMc());
		r.add("# P", res.getP());

		return r;
	}

	@Override
	public Solution getBestSolution() {
		return null;
	}

	@Override
	public String toString(){
		return this.getClass().getSimpleName() + "(" + constructive + ")";
	}
}
