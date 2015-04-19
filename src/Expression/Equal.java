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

    public int interpret(Map<String, Variable> variables)  {
        if (leftOperand.interpret(variables)==-1 || rightOperand.interpret(variables)==-1) return 1;
        return leftOperand.interpret(variables) == rightOperand.interpret(variables) ? 1 : 0;
    }
}
