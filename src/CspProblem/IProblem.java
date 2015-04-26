package CspProblem;

import Solvers.ISolver;

import java.util.Map;

/**
 * Created by Monis on 4/26/15.
 */
public interface IProblem {
    public abstract boolean checkConstraints();

    public abstract void addEvaluatorsToVariables();

    public Map<String,? extends Object> getVariablesMap();

    public ISolver resolveBTSolver();

    public ISolver resolverFCSolver();
}
