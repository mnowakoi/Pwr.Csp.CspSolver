package Expression;

import CspProblem.Variable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class StringVariableReference implements Expression {
    private String name;

    public StringVariableReference(String name)       { this.name = name; }

    public Integer interpret(Map<String, Variable> variables)  {
        return variables.get(name).getValue();
    }
}
