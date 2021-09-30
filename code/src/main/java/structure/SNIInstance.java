package structure;

import grafo.optilib.structure.Instance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SNIInstance implements Instance {

    private String name;
    private HashMap<Integer, List<Integer>> graph;
    private HashMap<Integer, HashSet<Integer>> graphAux;
    private int nodes,edges;
    private Set<Integer> s = new LinkedHashSet<>();
    private Map<Integer,Integer> changeNodeID = new HashMap<>();
    private Map<Integer,Integer> changeNodeIDReverse = new HashMap<>();
    private Map<Integer,Integer> nodeSize = new HashMap<>();


    public SNIInstance(String path) {
        this.graph = new HashMap<>();
        this.graphAux = new HashMap<>();
        readInstance(path);
    }

    @Override
    public void readInstance(String path) {

        this.name = path.substring(path.lastIndexOf('\\') + 1);
        System.out.println("Reading instance: " + this.name);
        FileReader fr= null;
        int nodeCnt = 0;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br=new BufferedReader(fr);
        // read line by line
        String line;
        try{
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split("\t");
                if(index>3) {
                    int from = Integer.parseInt(numbers[0]);
                    int to = Integer.parseInt(numbers[1]);
                    if(changeNodeID.get(to)==null){
                        changeNodeID.put(to,nodeCnt);
                        changeNodeIDReverse.put(nodeCnt,to);
                        nodeCnt++;
                    }
                    to = changeNodeID.get(to);
                    if(changeNodeID.get(from)==null){
                        changeNodeID.put(from,nodeCnt);
                        changeNodeIDReverse.put(nodeCnt,from);
                        nodeCnt++;
                    }
                    from = changeNodeID.get(from);

                    if(graph.get(from)==null){
                        List<Integer> nl = new ArrayList<>();
                        nl.add(to);
                        graph.put(from,nl);
                        HashSet<Integer> aristas = new HashSet<>();
                        aristas.add(to);
                        graphAux.put(from,aristas);
                    }
                    else{
                        graph.get(from).add(to);
                        graphAux.get(from).add(to);
                    }
                    s.add(from);
                    s.add(to);
                }
                else if(index==3){
                    this.nodes = Integer.parseInt(numbers[0]);
                    this.edges = Integer.parseInt(numbers[1]);
                }
                index++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        for(int node=0; node<this.nodes;node++){
            if(graph.get(node)==null) {
                graph.put(node, new ArrayList<>());
                graphAux.put(node,new HashSet<Integer>());
            }
            for(int j=0; j<graph.get(node).size();j++){
                graphAux.get(node).add(graph.get(node).get(j));
            }
            nodeSize.put(node,graph.get(node).size());
        }
        graph.clear();
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Integer, HashSet<Integer>>getGraph() {
        return graphAux;
    }

    public int getNumNodes() {
        return s.size();
    }

    public int getEdges() {
        return edges;
    }

    public Set<Integer> getNodes(){
        return s;
    }

    public Map<Integer, Integer> getNodeSize() {
        return nodeSize;
    }

}
