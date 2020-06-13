package payroll.domain;

import java.util.ArrayList;
import java.util.List;

public class Payroll {

    // Salary is a list of 100,000(items) random numbers
    private int items;
    private long sortingTime;
    private String sortingStrategy;
    private List<Integer> salaries = new ArrayList<Integer>();

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public long getSortingTime() {
        return sortingTime;
    }

    public void setSortingTime(long sortingTime) {
        this.sortingTime = sortingTime;
    }

    public String getSortingStrategy() {
        return sortingStrategy;
    }

    public void setSortingStrategy(String sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public List<Integer> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Integer> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "Payroll [items=" + items + ", sortingTime=" + sortingTime + ", sortingStrategy=" + sortingStrategy
                + ", salaries=" + salaries + "]";
    }

}