package structure;

import grafo.optilib.structure.Solution;
import grafo.optilib.tools.RandomManager;

import java.util.*;

public class SNISolution implements Solution {

    private int nodes,edges,k,mc,activeNodes;
    private double p,spread;
    private SNIInstance instance;
    private Double mark;
    private HashSet<Integer> SelectedNodes;

    public SNISolution(SNIInstance instance, HashSet<Integer> S, int k, int mc, double p) {
        this.instance = instance;
        this.nodes = instance.getNumNodes();
        this.edges = instance.getEdges();
        this.mark = null;
        this.k = k;
        this.mc = mc;
        this.p = p;
        this.activeNodes = 0;
        if(S==null) this.SelectedNodes = new HashSet<>();
        else this.SelectedNodes = new HashSet<>(S);
    }
    public SNISolution(SNISolution n){
        copy(n);
    }
    public void copy(SNISolution sol){
        this.instance = sol.instance;
        this.nodes = instance.getNumNodes();
        this.edges = instance.getEdges();
        this.mark = sol.mark;
        this.SelectedNodes = new HashSet<>(sol.getSelectedNodes());
        this.k = sol.k;
        this.mc = sol.mc;
        this.p = sol.p;
        this.activeNodes = sol.activeNodes;
        this.spread = sol.spread ;
    }

    @SuppressWarnings("Duplicates")
    public Double IndependentCascade(HashSet<Integer>  S, double p, int mc){
        RandomManager.getRandom();
        double sum = 0;
        for(int i=0; i<mc;i++) {
            //Set the Random object seed
            RandomManager.setSeed(i);
            //Simulate propagation process
            ArrayList<Integer> new_active = new ArrayList<>(S);
            HashSet<Integer> A = new HashSet<>(S);
            while (new_active.size()!=0) {
                //For each newly active node, find its neighbors that become activated
                ArrayList<Integer> new_ones = new ArrayList<>();
                for(Integer node : new_active) {
                    //Get random list of values [0,1]
                    for (int count:instance.getGraph().get(node)) {
                        double v = RandomManager.getRandom().nextFloat();
                        if(v<=p) new_ones.add(count);
                    }
                }
                //Add intersection  S = {1,2,3,6} S1 = {1,2,4} -> {3,6}
                new_active = new ArrayList<>();
                for (Integer t : new_ones) {
                    if(!A.contains(t)) {
                        new_active.add(t);
                    }
                }
                //Add newly activated nodes to the set of activated nodes
                A.addAll(new_active);
            }
            sum+=A.size();
        }
        this.spread = sum/(double)mc;
        this.activeNodes = (int)this.spread;
        return this.spread;
    }

    public Double getMark(){
        if(this.mark==null) return this.mark = IndependentCascade(SelectedNodes,p,mc);
        return this.mark;
    }
    public Double revaluateMark(){
        return this.mark = IndependentCascade(SelectedNodes,p,mc);
    }

    public HashSet<Integer> getSelectedNodes() {
        return SelectedNodes;
    }

    public Double getSpreadValue() {
        return spread;
    }

    public int getK() {
        return k;
    }

    public SNIInstance getInstance() {
        return instance;
    }

    public int getMc() {
        return mc;
    }

    public double getP() {
        return p;
    }

    public int getActiveNodes() {
        return activeNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SNISolution)) return false;
        SNISolution that = (SNISolution) o;
        return Objects.equals(SelectedNodes, that.SelectedNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SelectedNodes);
    }

}
