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
        String left = leftOperand.interpretString(variables);
        String right = rightOperand.interpretString(variables);
        return  left == null || right == null || !left.equals(right) ? "true" : "false";
    }
}