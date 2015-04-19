package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/19/15.
 */
class VariableReference implements Expression {
    private String name;

    public VariableReference(String name)       { this.name = name; }

    public int interpret(Map<String, Variable> variables)  {
        return variables.get(name).getValue();
    }
}
