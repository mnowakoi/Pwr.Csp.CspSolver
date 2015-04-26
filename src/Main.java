import CspProblem.IProblem;
import Parser.Parser;
import Solvers.ISolver;

import java.io.FileNotFoundException;

/**
 * Created by Monis on 4/17/15.
 */
public class Main {
     public static void main(String[] args) throws FileNotFoundException
     {
         Parser parser = new Parser("Hetmany.Txt");
         IProblem problem = parser.parseProblem();

//         String[] table = new String[0];
//         CspProblem.IProblem problem1 = new CspProblem.CspProblem(new String[0],new HashMap<>(),new StackMachine());
//         problem1.getVariablesMap();
//

         ISolver solverBT = problem.resolveBTSolver();

         System.out.println("BT Solve All:");
         long start_timeBTAll = System.nanoTime();
         int solvedBTAll = solverBT.solveAll();
         System.out.println("Solutions: " + solvedBTAll);
         long end_timeBTAll = System.nanoTime();
         double differenceBTAll = (end_timeBTAll-start_timeBTAll)/1e6;
         System.out.println("Time it took: " + differenceBTAll);

//         System.out.println("BT Solve Single:");
//         long start_timeBTSingle = System.nanoTime();
//         boolean solvedBTSingle = solverBT.solveSingle();
//         System.out.println("Do solutions exists: " + solvedBTSingle);
//         long end_timeBTSingle = System.nanoTime();
//         double differenceBTSingle = (end_timeBTSingle-start_timeBTSingle)/1e6;
//         System.out.println("Time it took: " + differenceBTSingle);

         ISolver solverFC = problem.resolverFCSolver();

         System.out.println("FC Solve All:");
         long start_timeFCAll = System.nanoTime();
         int solvedFCAll = solverFC.solveAll();
         System.out.println("Solutions: " + solvedFCAll);
         long end_timeFCAll = System.nanoTime();
         double differenceFCAll = (end_timeFCAll-start_timeFCAll)/1e6;
         System.out.println("Time it took: " + differenceFCAll);

//         System.out.println("FC Solve Single:");
//         long start_timeFCSingle = System.nanoTime();
//         boolean solvedFCSingle = solverFC.solveSingle();
//         System.out.println("Do solutions exists: " + solvedFCSingle);
//         long end_timeFCSingle = System.nanoTime();
//         double differenceFCSingle = (end_timeFCSingle-start_timeFCSingle)/1e6;
//         System.out.println("Time it took: " + differenceFCSingle);
     }
}
