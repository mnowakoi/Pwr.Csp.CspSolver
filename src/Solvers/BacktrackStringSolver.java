package Solvers;


import CspProblem.StringCspProblem;
import CspProblem.StringVariable;

/**
 * Created by Monis on 4/26/15.
 */
public class BacktrackStringSolver implements ISolver{
    StringCspProblem problem;
    int countOfSolutions;

    public BacktrackStringSolver(StringCspProblem problem) {
        this.problem = problem;
        this.countOfSolutions = 0;
    }

    public boolean solveSingle() {
        return solveSingleRecursion(-1);
    }

    public int solveAll() {
        this.countOfSolutions = 0;
        solveAllRecursion(-1);
        return countOfSolutions;
    }

    public boolean solveSingleRecursion(int lastAssignedIndex) {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        StringVariable nextVariable = problem.variablesMap.get(nextVariableName);

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

    public void solveAllRecursion(int lastAssignedIndex) {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        StringVariable nextVariable = problem.variablesMap.get(nextVariableName);

        while (nextVariable.hasNextDomainValue()) {
            nextVariable.setNextDomainValue();

            if (problem.checkConstraints()) {
                if (problem.variables.length - 1 == nextIndex) {
                    //TODO: spisywanie do pliku
                    //printSolution();

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
            System.out.println("Wartosc " + variable + " " + problem.variablesMap.get(variable).getValue());
        }
        System.out.println();
    }
}
