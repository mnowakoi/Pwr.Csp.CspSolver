package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
public class Absolute implements Expression {
    Expression leftOperand;
    public Absolute(Expression left) {
        leftOperand = left;
    }

    public Integer interpret(Map<String, Variable> variables)  {
        int interpretedValue = leftOperand.interpret(variables);
        return interpretedValue > 0 ? interpretedValue : -interpretedValue;
    }
}
