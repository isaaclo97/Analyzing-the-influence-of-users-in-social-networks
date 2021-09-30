package constructives;

import grafo.optilib.metaheuristics.Constructive;
import grafo.optilib.tools.RandomManager;
import structure.SNIInstance;
import structure.SNISolution;

import java.util.*;

public class GRASPConstructive implements Constructive<SNIInstance, SNISolution> {

    private Integer k,mc;
    private Double p,alpha;
    public GRASPConstructive(int k, int mc, Double p, double alpha){
        this.k = k;
        this.mc = mc;
        this.p = p;
        this.alpha = alpha;
    }

    class Candidate{
        private int node;
        private int value;
        Candidate(int node,int value){
            this.node = node;
            this.value = value;
        }
        public int getNode() {
            return node;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @SuppressWarnings("Duplicates")
    public SNISolution constructSolution(SNIInstance instance) {
        double realAlpha;
        if(this.alpha==-1) {
            realAlpha=RandomManager.getRandom().nextDouble();
        } else realAlpha = alpha;
        HashSet<Integer> S = new HashSet<>();
        List<Candidate> CL = new ArrayList<>();
        List<Candidate> reference = new ArrayList<>();
        for(Integer node: instance.getNodes()){
            int sum = 0;
            for (Integer el : instance.getGraph().get(node)) {
                sum += instance.getNodeSize().get(el);
            }
            Candidate addCandidate = new Candidate(node,sum);
            CL.add(addCandidate);
            reference.add(addCandidate);
        }
        CL.sort((c1, c2) -> -Integer.compare(c1.value, c2.value));
        for(int i=0; i<k;i++){
            int gmax = CL.get(CL.size()-i-1).value;
            int gmin = CL.get(0).value;
            //double umin = gmin+alpha*(gmax-gmin);
            double umax = gmax-realAlpha*(gmax-gmin);
            int limit = 0;
            while (limit < CL.size() && CL.get(limit).value >= umax) {
                limit++;
            }
            int randomIndex = RandomManager.getRandom().nextInt(limit);
            int selectedNode = CL.get(randomIndex).getNode();
            CL.get(randomIndex).setValue(-1);
            S.add(selectedNode);
            for(Integer node: instance.getGraph().get(selectedNode)){
                Candidate res = reference.get(node);
                if(res.getValue()!=-1) {
                    res.setValue(res.getValue() - instance.getNodeSize().get(selectedNode));
                    for(Integer nodeInterno: instance.getGraph().get(node)){
                        Candidate resAux = reference.get(nodeInterno);
                        if(resAux.getValue()!=-1) {
                            resAux.setValue(resAux.getValue()-1);
                        }
                    }
                }
            }
            CL.sort((c1, c2) -> -Integer.compare(c1.value, c2.value));
        }
        SNISolution s = new SNISolution(instance,S,k,mc,p);
        return s;
    }
}
