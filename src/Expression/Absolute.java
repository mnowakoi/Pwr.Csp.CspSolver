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
        Integer interpretedValue = leftOperand.interpret(variables);
        if(interpretedValue!=null) {
            interpretedValue = interpretedValue > 0 ? interpretedValue : -interpretedValue;
        }
        return interpretedValue;
    }
}
