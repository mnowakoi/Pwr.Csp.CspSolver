package StringExpression;

import CspProblem.StringVariable;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public class Text implements StringExpression {
    private String text;
    public Text(String text)       { this.text = text; }
    public String interpretString(Map<String, StringVariable> variables) {
        return text;
    }
}