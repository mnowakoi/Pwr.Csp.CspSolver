import CspProblem.CspProblem;
import Parser.Parser;
import Solvers.BacktrackSolver;

import java.io.FileNotFoundException;

/**
 * Created by Monis on 4/17/15.
 */
public class Main {
     public static void main(String[] args) throws FileNotFoundException
     {
         Parser parser = new Parser();
         CspProblem problem = parser.parseProblem();
         BacktrackSolver solver = new BacktrackSolver(problem);
         int solved = solver.solveAll();
         // solved =  solver.solveSingle();
     }
}
