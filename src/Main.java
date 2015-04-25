import CspProblem.CspProblem;
import Parser.Parser;
import Solvers.BacktrackSolver;
import Solvers.ForwardCheckingSolver;

import java.io.FileNotFoundException;

/**
 * Created by Monis on 4/17/15.
 */
public class Main {
     public static void main(String[] args) throws FileNotFoundException
     {
         Parser parser = new Parser("Hetmany.Txt");
         CspProblem problem = parser.parseProblem();
         BacktrackSolver solver = new BacktrackSolver(problem);

         System.out.println("Zaczynamy");
         long start_time = System.nanoTime(); //System.currentTimeMillis();
         int solved = solver.solveAll();
        // boolean solvable = solver.solveSingle();
         System.out.println("Solutions: " + solved);
         long end_time = System.nanoTime(); //  System.currentTimeMillis();
         double difference = (end_time-start_time)/1e6;
         System.out.println("Czas: " + difference);

         ForwardCheckingSolver fcsolver = new ForwardCheckingSolver(problem);
         System.out.println("Zaczynamy");
         start_time = System.nanoTime(); //System.currentTimeMillis();
         solved = fcsolver.solveAll();
         // solvable = fcsolver.solveSingle();
         System.out.println("Solutions: " + solved);
         end_time = System.nanoTime(); //  System.currentTimeMillis();
         difference = (end_time-start_time)/1e6;
         System.out.println("Czas: " + difference);

         // solved =  solver.solveSingle();
     }
}
