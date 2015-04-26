package StringExpression;

import CspProblem.StringVariable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class StringVariableReference implements StringExpression {
    private String name;

    public StringVariableReference(String name)       { this.name = name; }

    public String interpretString(Map<String, StringVariable> variables)  {
        return variables.get(name).getValue();
    }
}
