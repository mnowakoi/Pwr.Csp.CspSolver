package Solvers;

import CspProblem.CspProblem;
import CspProblem.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monis on 4/24/15.
 */
public class ForwardCheckingSolver {
    CspProblem problem;
    int countOfSolutions;

    public ForwardCheckingSolver(CspProblem problem)
    {
        this.problem = problem;
        this.countOfSolutions = 0;
    }

    public int solveAll()
    {
        this.countOfSolutions = 0;
        solveAllRecursion(-1);
        return countOfSolutions;
    }

    public void solveAllRecursion(int lastAssignedIndex)
    {
        int nextIndex = lastAssignedIndex + 1;
        String nextVariableName = problem.variables[nextIndex];
        Variable nextVariable = problem.variablesMap.get(nextVariableName);

        HashMap<String, ArrayList<Integer>> currentlyDeleted = new HashMap<>();

        for(int i = nextIndex; i < problem.variables.length; i++)
        {
            String currentVariableName = problem.variables[i];
            Variable currentVariable = problem.variablesMap.get(nextVariableName);
            ArrayList<Integer> incorrectValues = new ArrayList<>();

            while (currentVariable.hasNextDomainValue()) {
                currentVariable.setNextDomainValue();
                if (!problem.checkConstraints()) {
                    incorrectValues.add(currentVariable.getValue());
                    currentVariable.deleteFromDomain(currentVariable.getValue());
                    currentVariable.setValue(null);
                }
            }

            if (incorrectValues.size() > 0) {
                currentlyDeleted.put(currentVariableName, incorrectValues);
            }

            currentVariable.setValue(null);
            if (!currentVariable.hasNextDomainValue()) {
                revertDomainValues(currentlyDeleted);
                return;
            }
        }

        while(nextVariable.hasNextDomainValue())
        {
            nextVariable.setNextDomainValue();
                if (problem.variables.length - 1 == nextIndex)
                {
///                   //TODO: spisywanie do pliku
//                    for(String variable : problem.variables)
//                    {
//                        System.out.println("Wartosc " + variable + " " + problem.variablesMap.get(variable).getValue());
//                    }
//                    System.out.println();

                    this.countOfSolutions++;
                    if(!nextVariable.hasNextDomainValue()) {
                        nextVariable.setValue(null);
                        revertDomainValues(currentlyDeleted);
                        return;
                    }
                }
                else
                {
                    solveAllRecursion(nextIndex);
                }
        }
        nextVariable.setValue(null);
        revertDomainValues(currentlyDeleted);
    }

    private void revertDomainValues(HashMap<String, ArrayList<Integer>> incorrectValues)
    {
        for(Map.Entry<String, ArrayList<Integer>> entry : incorrectValues.entrySet())
        {
            String variableName = entry.getKey();
            Variable revertedVariable = problem.variablesMap.get(variableName);

            ArrayList<Integer> valuesToAdd = entry.getValue();
            valuesToAdd.forEach(revertedVariable::addToDomain);
        }
    }
}
