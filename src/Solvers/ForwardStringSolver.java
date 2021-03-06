package Solvers;

import CspProblem.StringCspProblem;
import CspProblem.StringVariable;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monis on 4/24/15.
 */
public class ForwardStringSolver implements ISolver {
    StringCspProblem problem;
    int countOfSolutions;
    String outputPath;
    final String path = "/Users/Monis/IdeaProjects/Pwr.Csp.CspSolver/src/ProblemSamples/";

    public ForwardStringSolver(StringCspProblem problem) {
        this.problem = problem;
        this.problem.addEvaluatorsToVariables();
        this.countOfSolutions = 0;
        this.outputPath = path + "Solutions.Txt";
    }

    public int solveAll() {
        this.clearFile();
        this.countOfSolutions = 0;
        solveAllRecursion(-1);
        return countOfSolutions;
    }

    public boolean solveSingle()
    {
        return solveSingleRecursion(-1);
    }

    private boolean solveSingleRecursion(int lastAssignedIndex) {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        StringVariable nextVariable = problem.variablesMap.get(nextVariableName);

        HashMap<String, ArrayList<String>> currentlyDeleted = new HashMap<>();

        for (int i = nextIndex; i < problem.variables.length; i++) {
            if (isDomainVariableEmpty(i, currentlyDeleted)) {
                revertDomainValues(currentlyDeleted);
                return false;
            }
        }

        while (nextVariable.hasNextDomainValue()) {
            nextVariable.setNextDomainValue();

            if (problem.variables.length - 1 == nextIndex) {

                printSolution();
                return true;
            }
            else {
                if(solveSingleRecursion(nextIndex)) {
                    return true;
                }
            }
        }
        nextVariable.setValue(null);
        revertDomainValues(currentlyDeleted);
        return false;
    }

    private boolean isDomainVariableEmpty(int i, HashMap<String, ArrayList<String>> currentlyDeleted) {
        String currentVariableName = problem.variables[i];
        StringVariable currentVariable = problem.variablesMap.get(currentVariableName);
        ArrayList<String> incorrectValues = new ArrayList<>();

        while (currentVariable.hasNextDomainValue()) {
            currentVariable.setNextDomainValue();

            if (!currentVariable.isConflictingVariable(problem.variablesMap)) {
                incorrectValues.add(currentVariable.getValue());
                currentVariable.deleteFromDomain(currentVariable.getValue());
                currentVariable.setValue(null);
            }
        }

        if (incorrectValues.size() > 0) {
            currentlyDeleted.put(currentVariableName, incorrectValues);
        }

        currentVariable.setValue(null);
        return !currentVariable.hasNextDomainValue();
    }

    private void solveAllRecursion(int lastAssignedIndex) {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        StringVariable nextVariable = problem.variablesMap.get(nextVariableName);

        HashMap<String, ArrayList<String>> currentlyDeleted = new HashMap<>();

        for (int i = nextIndex; i < problem.variables.length; i++) {
            if (isDomainVariableEmpty(i, currentlyDeleted)) {
                revertDomainValues(currentlyDeleted);
                return;
            }
        }

        while (nextVariable.hasNextDomainValue()) {
            nextVariable.setNextDomainValue();

            if (problem.variables.length - 1 == nextIndex) {

                printSolutionIntoFile();
                this.countOfSolutions++;
            }
            else {
                solveAllRecursion(nextIndex);
            }
        }
        nextVariable.setValue(null);
        revertDomainValues(currentlyDeleted);
    }

    private void revertDomainValues(HashMap<String, ArrayList<String>> incorrectValues) {
        for (Map.Entry<String, ArrayList<String>> entry : incorrectValues.entrySet()) {
            String variableName = entry.getKey();
            StringVariable revertedVariable = problem.variablesMap.get(variableName);

            ArrayList<String> valuesToAdd = entry.getValue();
            valuesToAdd.forEach(revertedVariable::addToDomain);
        }
    }

    private void printSolution() {
        for (String variable : problem.variables) {
            System.out.println("Wartosc " + variable + " " + problem.variablesMap.get(variable).getValue());
        }
        System.out.println();
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
