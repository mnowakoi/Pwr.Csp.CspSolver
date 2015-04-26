package StringExpression;

import CspProblem.StringVariable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class StringEqual implements StringExpression{
    StringExpression leftOperand;
    StringExpression rightOperand;

    public StringEqual(StringExpression left, StringExpression right) {
        leftOperand = left;
        rightOperand = right;
    }

    @Override
    public String interpretString(Map<String, StringVariable> variables)  {
        return leftOperand.interpretString(variables).equals(rightOperand.interpretString(variables)) ? "true" : "false";
    }
}