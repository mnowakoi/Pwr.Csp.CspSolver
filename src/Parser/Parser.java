package Parser;

import CspProblem.*;
import Expression.Evaluator;
import Expression.StackMachine;
import StringExpression.StackMachineString;
import StringExpression.StringEvaluator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Monis on 4/17/15.
 */
public class Parser {
    String path = "/Users/Monis/IdeaProjects/Pwr.Csp.CspSolver/src/ProblemSamples/";

    public Parser()
    {
        this.path = "/Users/Monis/IdeaProjects/Pwr.Csp.CspSolver/src/ProblemSamples/BasicProblem.Txt";
    }

    public Parser(String fileName)
    {
        this.path = this.path + fileName;
    }

    public IProblem parseProblem() throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(path));
        String[] variables = scanner.nextLine().split(" ");
        if(scanner.hasNextInt()) {
            HashMap<String, Variable> variablesMap = createVariablesMap(variables);
            for (String variable : variables) {
                variablesMap.get(variable).setDomain(getNextDomain(scanner));
            }
            StackMachine stackMachine = new StackMachine();
            while (scanner.hasNextLine() && scanner.hasNext()) {

                Evaluator evaluator = new Evaluator(scanner.nextLine());
                stackMachine.addEvaluator(evaluator);
            }
            return new CspProblem(variables, variablesMap, stackMachine);
        }
        HashMap<String, StringVariable> variablesMap = createStringVariablesMap(variables);
        for (String variable : variables) {
            variablesMap.get(variable).setDomain(getNextStringDomain(scanner));
        }
        StackMachineString stackMachine = new StackMachineString();
        while (scanner.hasNextLine() && scanner.hasNext()) {

            StringEvaluator evaluator = new StringEvaluator(scanner.nextLine(), Arrays.asList(variables));
            stackMachine.addEvaluator(evaluator);
        }
        return new StringCspProblem(variables, variablesMap, stackMachine);
    }

    private HashMap<String, Variable> createVariablesMap(String[] variableNames)
    {
        HashMap<String, Variable> variablesMap = new HashMap<String, Variable>();
        for(String name : variableNames)
        {
            variablesMap.put(name, new Variable(name));
        }
        return variablesMap;
    }

    private HashMap<String, StringVariable> createStringVariablesMap(String[] variableNames)
    {
        HashMap<String, StringVariable> variablesMap = new HashMap<>();
        for(String name : variableNames)
        {
            variablesMap.put(name, new StringVariable(name));
        }
        return variablesMap;
    }

    private ArrayList<Integer> getNextDomain(Scanner scanner)
    {
            ArrayList<Integer> domain = new ArrayList<>();
            String domainLine = scanner.nextLine();
            String[] domainElements = domainLine.split(" ");
            for(String domainElem : domainElements)
            {
                try
                {
                    domain.add(Integer.parseInt(domainElem));
                } catch(NumberFormatException nfe)
                {
                }
            }
        return domain;
    }

    private ArrayList<String> getNextStringDomain(Scanner scanner)
    {
        ArrayList<String> domain = new ArrayList<>();
        String domainLine = scanner.nextLine();
        String[] domainElements = domainLine.split(" ");
        for(String domainElem : domainElements)
        {
            domain.add(domainElem);
        }
        return domain;
    }
}
