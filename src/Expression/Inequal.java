package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
public class Inequal implements Expression {
    Expression leftOperand;
    Expression rightOperand;

    public Inequal(Expression left, Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public Integer interpret(Map<String, Variable> variables)  {
        if (leftOperand.interpret(variables)==-1 || rightOperand.interpret(variables)==-1) return 1;
        return leftOperand.interpret(variables) != rightOperand.interpret(variables) ? 1 : 0;
    }
}
