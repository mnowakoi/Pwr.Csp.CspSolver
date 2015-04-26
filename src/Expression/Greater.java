package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
public class Greater implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Greater(Expression left, Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public Integer interpret(Map<String, Variable> variables)  {
        Integer left = leftOperand.interpret(variables);
        Integer right = rightOperand.interpret(variables);
        return left == null || right == null || left > right ? 1 : 0;
    }
}
