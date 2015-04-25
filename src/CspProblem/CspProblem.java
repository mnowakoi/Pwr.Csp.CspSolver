package CspProblem;

import Expression.Evaluator;
import Expression.StackMachine;

import java.util.HashMap;

/**
 * Created by Monis on 4/17/15.
 */
public class CspProblem {
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

    public boolean addEvaluatorsToVariables()
    {
        for(Evaluator evaluator : stackMachine.constraintList)
        {
            evaluator.addReferenceToVariable(variablesMap);
        }
        return true;
    }
}