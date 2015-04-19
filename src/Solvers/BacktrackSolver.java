package Solvers;

import CspProblem.CspProblem;
import CspProblem.Variable;

/**
 * Created by Monis on 4/19/15.
 */
public class BacktrackSolver {
    CspProblem problem;
    int countOfSolutions;

    public BacktrackSolver(CspProblem problem)
    {
        this.problem = problem;
        this.countOfSolutions = 0;
    }

    public boolean solveSingle()
    {
        return solveSingleRecursion(-1);
    }

    public int solveAll()
    {
        this.countOfSolutions = 0;
        solveAllRecursion(-1);
        return countOfSolutions;
    }

    public boolean solveSingleRecursion(int lastAssignedIndex)
    {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        Variable nextVariable = problem.variablesMap.get(nextVariableName);

        while(nextVariable.hasNextDomainValue())
        {
            nextVariable.setNextDomainValue();
            if(problem.checkConstraints()) {
                if (problem.variables.length - 1 == nextIndex)
                {
                    for(String variable : problem.variables)
                    {
                        System.out.println("Wartosc " + variable + " " + problem.variablesMap.get(variable).getValue());
                    }
                    System.out.println();
                    nextVariable.setValue(null);

                    return true;
                }
                else
                {
                    if(solveSingleRecursion(nextIndex))
                    {
                        return true;
                    }
                }
            }
        }
        nextVariable.setValue(null);
        return false;
    }

    public void solveAllRecursion(int lastAssignedIndex)
    {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        Variable nextVariable = problem.variablesMap.get(nextVariableName);

        while(nextVariable.hasNextDomainValue())
        {
            nextVariable.setNextDomainValue();
            if(problem.checkConstraints()) {
                if (problem.variables.length - 1 == nextIndex)
                {
                    //TODO: spisywanie do pliku
                    for(String variable : problem.variables)
                    {
                        System.out.println("Wartosc " + variable + " " + problem.variablesMap.get(variable).getValue());
                    }
                    System.out.println();
                    this.countOfSolutions++;
                    if(!nextVariable.hasNextDomainValue()) {
                        nextVariable.setValue(null);
                        return;
                    }
                }
                else
                {
                    solveAllRecursion(nextIndex);
                }
            }
        }
        nextVariable.setValue(null);
        // return;
    }
}
