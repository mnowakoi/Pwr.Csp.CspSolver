package Solvers;

import CspProblem.CspProblem;
import CspProblem.Variable;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Monis on 4/19/15.
 */
public class BacktrackSolver implements ISolver {
    CspProblem problem;
    int countOfSolutions;
    String outputPath;
    final String path = "/Users/Monis/IdeaProjects/Pwr.Csp.CspSolver/src/ProblemSamples/";


    public BacktrackSolver(CspProblem problem) {
        this.problem = problem;
        this.countOfSolutions = 0;
        this.outputPath = path + "Solutions.Txt";
    }

    public boolean solveSingle() {
        return solveSingleRecursion(-1);
    }

    public int solveAll() {
        clearFile();
        this.countOfSolutions = 0;
        solveAllRecursion(-1);
        return countOfSolutions;
    }

    private boolean solveSingleRecursion(int lastAssignedIndex) {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        Variable nextVariable = problem.variablesMap.get(nextVariableName);

        while (nextVariable.hasNextDomainValue()) {
            nextVariable.setNextDomainValue();

            if (problem.checkConstraints()) {
                if (problem.variables.length - 1 == nextIndex) {
                    printSolution();
                    nextVariable.setValue(null);

                    return true;
                } else {
                    if (solveSingleRecursion(nextIndex)) {
                        return true;
                    }
                }
            }
        }
        nextVariable.setValue(null);
        return false;
    }

    private void solveAllRecursion(int lastAssignedIndex) {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        Variable nextVariable = problem.variablesMap.get(nextVariableName);

        while (nextVariable.hasNextDomainValue()) {
            nextVariable.setNextDomainValue();

            if (problem.checkConstraints()) {
                if (problem.variables.length - 1 == nextIndex) {

                    printSolutionIntoFile();
                    this.countOfSolutions++;
                } else {
                    solveAllRecursion(nextIndex);
                }
            }
        }
        nextVariable.setValue(null);
    }

    private void printSolution() {
        for (String variable : problem.variables) {
            System.out.print(variable + "=" + problem.variablesMap.get(variable).getValue() + " ");
        }
        System.out.println("\n");
    }

    protected void printSolutionIntoFile()
    {
        StringBuffer output = new StringBuffer();
        for (String variable : problem.variables) {
            output.append(variable).append("=").append(problem.variablesMap.get(variable).getValue()).append(" ");
        }
        output.append("\n\n");
        try {
            FileWriter fileOut = new FileWriter(outputPath, true);
            fileOut.write(output.toString());
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void clearFile()
    {
        try {
            PrintWriter fileOut = new PrintWriter(outputPath);
            StringBuffer output = new StringBuffer();
            fileOut.println(output.toString());
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
