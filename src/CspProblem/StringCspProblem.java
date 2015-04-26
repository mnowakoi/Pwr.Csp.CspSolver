package CspProblem;

import Solvers.BacktrackStringSolver;
import Solvers.ForwardStringSolver;
import Solvers.ISolver;
import StringExpression.StackMachineString;
import StringExpression.StringEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */

public class StringCspProblem implements IProblem{
    public String[] variables;
    public HashMap<String, StringVariable> variablesMap;
    public StackMachineString stackMachine;

    public StringCspProblem(String[] variables, HashMap<String, StringVariable> variablesMap, StackMachineString stackMachine)
    {
        this.variables = variables;
        this.variablesMap = variablesMap;
        this.stackMachine = stackMachine;
    }

    public boolean checkConstraints()
    {
        for(StringEvaluator evaluator : stackMachine.constraintList)
        {
            if (evaluator.interpretString(variablesMap).equals("false")) return false;
        }
        return true;
    }

    public void addEvaluatorsToVariables()
    {
        for(StringEvaluator evaluator : stackMachine.constraintList)
        {
            evaluator.addReferenceToVariable(variablesMap);
        }
    }

    public Map<String, StringVariable> getVariablesMap() {
        return variablesMap;
    }

    @Override
    public ISolver resolveBTSolver() {
        return new BacktrackStringSolver(this);
    }

    @Override
    public ISolver resolverFCSolver() {
        return new ForwardStringSolver(this);
    }
}