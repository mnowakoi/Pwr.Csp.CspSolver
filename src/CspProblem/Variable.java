package CspProblem;

import java.util.List;

/**
 * Created by Monis on 4/17/15.
 */
public class Variable {
    private String name;
    private List<Integer> domain;
    private Integer value;

    public Variable(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDomain(List<Integer> domain)
    {
        this.domain = domain;
    }

    public List<Integer> getDomain()
    {
        return domain;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean hasValue()
    {
        return this.value!=null;
    }

    public boolean hasNextDomainValue()
    {
        return (this.value==null && this.domain.size() > 0) || (this.value != null && this.value != this.domain.get(this.domain.size()-1));
    }

    public void setNextDomainValue()
    {
        this.value = this.domain.get(this.domain.indexOf(value)+1);
    }
}
