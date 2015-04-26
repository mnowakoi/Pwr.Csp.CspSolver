package CspProblem;

import Expression.Evaluator;
import Expression.StackMachine;
import Solvers.BacktrackSolver;
import Solvers.ForwardCheckingSolver;
import Solvers.ISolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monis on 4/17/15.
 */
public class CspProblem implements IProblem{
    public String[] variables;
    public HashMap<String, Variable> variablesMap;
    public StackMachine stackMachine;

    public CspProblem(String[] variables, HashMap<String, Variable> variablesMap, StackMachine stackMachine)
    {
        this.variables = variables;
        this.variablesMap = variablesMap;
        this.stackMachine = stackMachine;
    }

    public boolean checkConstraints()
    {
        for(Evaluator evaluator : stackMachine.constraintList)
        {
            if (evaluator.interpret(variablesMap)==0) return false;
        }
        return true;
    }

    public void addEvaluatorsToVariables()
    {
        for(Evaluator evaluator : stackMachine.constraintList)
        {
            evaluator.addReferenceToVariable(variablesMap);
        }
    }

    public Map<String, Variable> getVariablesMap() {
        return variablesMap;
    }

    public ISolver resolveBTSolver() {
        return new BacktrackSolver(this);
    }

    public ISolver resolverFCSolver() {
        return new ForwardCheckingSolver(this);
    }
}