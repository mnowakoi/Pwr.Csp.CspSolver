package StringExpression;

import CspProblem.IStackMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monis on 4/26/15.
 */
public class StackMachineString extends IStackMachine {
    public List<StringEvaluator> constraintList;

    public StackMachineString()
    {
        constraintList = new ArrayList<StringEvaluator>();
    }

    public void addEvaluator(StringEvaluator evaluator)
    {
        constraintList.add(evaluator);
    }
}
