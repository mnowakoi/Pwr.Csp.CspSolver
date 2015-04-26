package StringExpression;

import CspProblem.StringVariable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class StringInequal implements StringExpression{
    StringExpression leftOperand;
    StringExpression rightOperand;

    public StringInequal(StringExpression left, StringExpression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public String interpretString(Map<String, StringVariable> variables)  {
        return leftOperand.interpretString(variables).equals(rightOperand.interpretString(variables)) ? "false" : "true";
    }
}