package StringExpression;

import CspProblem.StringVariable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class Number implements StringExpression {
    private int number;
    public Number(int number)       { this.number = number; }
    public String interpretString(Map<String, StringVariable> variables) {
        return number + "";
    }
}