package localSearch;

import structure.SNIInstance;
import structure.SNISolution;

import java.util.*;

public class SurrogateRandom {
    private int x;
    public SurrogateRandom(int x){
        this.x = x;
    }
    class Candidate implements Comparable<Candidate>{
        private int node;
        private double value;
        Candidate(int node,double value){
            this.node = node;
            this.value = value;
        }
        public int getNode() {
            return node;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Candidate)) return false;
            Candidate candidate = (Candidate) o;
            return node == candidate.node &&
                    Double.compare(candidate.value, value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, value);
        }

        public int compareTo(Candidate o) {
            return -Double.compare(this.value,o.value);
        }
    }

    public void improve(SNISolution sniSolution) {
        ArrayList<Candidate> selectedNodes = new ArrayList<>();
        ArrayList<Candidate> candidates = new ArrayList<>();
        SNIInstance instance = sniSolution.getInstance();
        for(Integer node: instance.getNodes()) {
            if (sniSolution.getSelectedNodes().contains(node)) {
                selectedNodes.add(new Candidate(node,(double)sniSolution.getInstance().getNodeSize().get(node)));
            } else {
                candidates.add(new Candidate(node,(double)sniSolution.getInstance().getNodeSize().get(node)));
            }
        }
        Collections.sort(candidates);
        SNISolution copySol = new SNISolution(sniSolution);
        surrogate(selectedNodes,candidates,sniSolution);
        sniSolution.revaluateMark();
        if(copySol.revaluateMark()>sniSolution.getMark()){
            sniSolution.copy(copySol);
        }
    }

    private void surrogate(ArrayList<Candidate> selectedNodes, ArrayList<Candidate> candidates, SNISolution sniSolution) {
        double mark = sniSolution.getMark();
        SNISolution best = new SNISolution(sniSolution.getInstance(),sniSolution.getSelectedNodes(),sniSolution.getK(),sniSolution.getMc(),sniSolution.getP());
        boolean improve = true;
        while(improve){
            improve = false;
            Collections.shuffle(selectedNodes);
            for(int i=0; i<selectedNodes.size() && !improve;i++){
                double N = Math.min(candidates.size(),x);
                for(int j=0; j<N && !improve;j++){
                    Candidate worstOfTheBest = selectedNodes.get(i);
                    Candidate bestOfTheWorst = candidates.get(j);
                    sniSolution.getSelectedNodes().remove((Integer)worstOfTheBest.getNode());
                    sniSolution.getSelectedNodes().add((Integer)bestOfTheWorst.getNode());
                    double curMark = sniSolution.revaluateMark();
                    if(Double.compare(mark,curMark)<0){
                        System.out.println("CurBest: " + curMark);
                        mark = curMark;
                        improve=true;
                        best.copy(sniSolution);
                        selectedNodes.set(i,bestOfTheWorst);
                        candidates.set(j,worstOfTheBest);
                    }else{
                        sniSolution.getSelectedNodes().add((Integer)worstOfTheBest.getNode());
                        sniSolution.getSelectedNodes().remove((Integer)bestOfTheWorst.getNode());
                    }
                }
            }
        }
        sniSolution.copy(best);
    }
}
