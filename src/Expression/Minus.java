package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
public class Minus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Minus(Expression left, Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public Integer interpret(Map<String, Variable> variables)  {
        return leftOperand.interpret(variables) - rightOperand.interpret(variables);
    }
}
