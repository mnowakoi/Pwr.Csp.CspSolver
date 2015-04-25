package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
class Number implements Expression {
    private int number;
    public Number(int number)       { this.number = number; }
    public Integer interpret(Map<String, Variable> variables)  { return number; }
}