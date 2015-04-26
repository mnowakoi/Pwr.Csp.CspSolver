package Expression;

import CspProblem.IStackMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monis on 4/19/15.
 */
public class StackMachine extends IStackMachine {
    public List<Evaluator> constraintList;

    public StackMachine()
    {
        constraintList = new ArrayList<Evaluator>();
    }

    public void addEvaluator(Evaluator evaluator)
    {
        constraintList.add(evaluator);
    }
}
