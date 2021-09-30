import algorithms.*;
import constructives.*;
import grafo.optilib.metaheuristics.Algorithm;
import grafo.optilib.results.Experiment;
import localSearch.SurrogateRandom;
import structure.SNIInstance;
import structure.SNIInstanceFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {
    public static void main(String[] args)  {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        String date = String.format("%04d-%02d-%02d T%02d-%02d", year, month, day, hour, minute);
        SNIInstanceFactory factory = new SNIInstanceFactory();

        String dir = ((args.length == 0) ? "instances" : (args[1] + "/"));
        String outDir = "experiments/" + date;
        File outDirCreator = new File(outDir);
        outDirCreator.mkdirs();
        String[] extensions = new String[]{".txt"};

        ArrayList<Algorithm<SNIInstance>> execution = new ArrayList<>();
        double p = 0.01;
        int mc = 100;
        int x = 20;
        for(int i=10; i<60;i+=10){
            int k = i;
            execution.add(new AlgConstructiveGraspImprovement(new GRASPConstructive(k,mc,p,-1),100,"Final Code", new SurrogateRandom(x)));
        }
        for (int i = 0; i < execution.size(); i++) {
            String outputFile = outDir + "/" + execution.get(i).toString() + "-K"+i+".xlsx";
            Experiment<SNIInstance, SNIInstanceFactory> experiment = new Experiment<>(execution.get(i), factory);
            experiment.launch(dir, outputFile, extensions);
        }
    }
}