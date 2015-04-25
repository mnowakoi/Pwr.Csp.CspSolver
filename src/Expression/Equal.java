package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
public class Equal implements Expression{
    Expression leftOperand;
    Expression rightOperand;

    public Equal(Expression left, Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public Integer interpret(Map<String, Variable> variables)  {
        return leftOperand.interpret(variables) == rightOperand.interpret(variables) ? 1 : 0;
    }

    private boolean areEqual(int left, int right)
    {
        return left == right;
    }

    private boolean areEqual(String left, String right)
    {
        return left.equals(right);
    }
}
