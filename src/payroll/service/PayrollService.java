package payroll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import payroll.domain.Payroll;
import payroll.utils.ServiceUtils;

/**
 * @author: Jai Ananda
 * @version: 1.0
 */
public class PayrollService {

    /* This is an instance of ServiceUtils */
    private ServiceUtils serviceUtils=new ServiceUtils();

    /**
     * This method is used to get payroll, accepts int as arguments, returns type of Payroll and does not throw any exception
     *
     * @param items
     * @return
     */
    public Payroll getPayroll(int items) {
        // create a payroll
        Payroll payroll = new Payroll();
        // add items to the payroll
        payroll.setItems(items);
        // create salaries
        List<Integer> salaries = new ArrayList<>();
        // add values to salaries
        for (int i = 0; i < items; i++) {
            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            int salary = ThreadLocalRandom.current().nextInt(1, 1000000);
            salaries.add(salary);
        }
        // set salaries to payroll
        payroll.setSalaries(salaries);
        // return payroll
        return payroll;
    }

    /**
     * This method is used to get sorted payroll, accepts Payroll, String as arguments, returns Payroll and does not throw any exception
     *
     * @param unsortedPayroll
     * @param sortingStrategy
     * @return
     */
    public Payroll getSortedPayroll(Payroll unsortedPayroll, String sortingStrategy) {
        // get unsorted salaries
        List<Integer> unsortedSalaries = unsortedPayroll.getSalaries();
        // initialize sorted salaries
        List<Integer> sortedSalaries = null;
        // remove all non-alphanumeric values
        sortingStrategy = removeAllNonAlphaNumeric(sortingStrategy);
        // initialize stop watch
        StopWatch stopWatch = new StopWatch();
        // start stop-watch
        stopWatch.start();
        // validate, assign sorting strategy and sort
        if(sortingStrategy.equalsIgnoreCase("bubble")) {
            sortedSalaries = new ArrayList<Integer>(serviceUtils.getByBubbleSort(unsortedSalaries));
        } else if(sortingStrategy.equalsIgnoreCase("selection")) {
            sortedSalaries = new ArrayList<Integer>(serviceUtils.getBySelectionSort(unsortedSalaries));
        } else if(sortingStrategy.equalsIgnoreCase("insertion")) {
            sortedSalaries = new ArrayList<Integer>(serviceUtils.getByInsertionSort(unsortedSalaries));
        } else {
            sortedSalaries = new ArrayList<Integer>(unsortedSalaries);
        }
        // stop stop-watch
        stopWatch.stop();
        // set sorting time to payroll
        unsortedPayroll.setSortingTime(stopWatch.getTime(TimeUnit.MILLISECONDS));
        // set sorting strategy to payroll
        unsortedPayroll.setSortingStrategy(sortingStrategy);
        // set sorted salaries to payroll
        unsortedPayroll.setSalaries(sortedSalaries);
        // return payroll
        return unsortedPayroll;
    }

    /**
     * This method is used to remove all non-alphanumeric characters, accepts String as input arguments, returns String and does not throw any exception
     *
     * @param string
     * @return
     */
    protected String removeAllNonAlphaNumeric(String string) {
        // replace all non-alphanumeric characters using regex
        return string.replaceAll("[^A-Za-z]", "");
    }

}
