package payroll.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import payroll.domain.Payroll;
import payroll.service.PayrollService;

public class ServiceUtilsTest {

    private static final Logger log = Logger.getLogger(ServiceUtilsTest.class.getName());

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        protected void succeeded(long nanos, Description description) {
            System.out.println(description.getMethodName() + " succeeded, time taken " + nanos);
        }

        /**
         * Invoked when a test fails
         */
        protected void failed(long nanos, Throwable e, Description description) {
            System.out.println(description.getMethodName() + " failed, time taken " + nanos);
        }

        /**
         * Invoked when a test is skipped due to a failed assumption.
         */
        protected void skipped(long nanos, AssumptionViolatedException e,
                               Description description) {
            System.out.println(description.getMethodName() + " skipped, time taken " + nanos);
        }

        /**
         * Invoked when a test method finishes (whether passing or failing)
         */
        protected void finished(long nanos, Description description) {
            System.out.println(description.getMethodName() + " finished, time taken " + nanos);
        }
    };

    @Before
    public void setup() {
        log.info("Setting up...");

    }

    @After
    public void tearDown() {
        log.info("Tearing down...");

    }

    @BeforeClass
    public static void before() {
        log.info("Running JUnit test cases from class: " + ServiceUtilsTest.class);

    }

    @AfterClass
    public static void after() {
        log.info("Testing class " + ServiceUtilsTest.class + " has completed.");

    }

    public void reset() {
        tearDown();
        setup();
    }

    @Test
    public void getByBubbleSortTest() {
//		int items = 1000;
//		ServiceUtils serviceUtils = new ServiceUtils();
//		PayrollService payrollService = new PayrollService();
//		Payroll unsortedPayroll = payrollService.getPayroll(items);
//		List<Integer> unsortedSalaries = unsortedPayroll.getSalaries();

        Payroll unsortedPayroll = new Payroll();
        unsortedPayroll.setItems(3);

        List<Integer> salaries = new ArrayList<Integer>();
        salaries.add(3000);
        salaries.add(2000);
        salaries.add(1500);
        unsortedPayroll.setSalaries(salaries);

        List<Integer> unsortedSalaries = unsortedPayroll.getSalaries();

        ServiceUtils serviceUtils = new ServiceUtils();

        List<Integer> bubbleSortedSalaries = serviceUtils.getByBubbleSort(unsortedSalaries);

        // size n = 1000?
        assertEquals(3, bubbleSortedSalaries.size());

        // salaries comparison
        assertEquals(Integer.valueOf(1500), bubbleSortedSalaries.get(0));
        assertEquals(Integer.valueOf(2000), bubbleSortedSalaries.get(1));
        assertEquals(Integer.valueOf(3000), bubbleSortedSalaries.get(2));

        reset();
    }

    @Test
    public void getBySelectionSortTest() {
        Payroll unsortedPayroll = new Payroll();
        unsortedPayroll.setItems(3);

        List<Integer> salaries = new ArrayList<Integer>();
        salaries.add(3000);
        salaries.add(2000);
        salaries.add(1500);
        unsortedPayroll.setSalaries(salaries);

        List<Integer> unsortedSalaries = unsortedPayroll.getSalaries();

        ServiceUtils serviceUtils = new ServiceUtils();

        List<Integer> bubbleSortedSalaries = serviceUtils.getBySelectionSort(unsortedSalaries);

        // size n = 1000?
        assertEquals(3, bubbleSortedSalaries.size());

        // salaries comparison
        assertEquals(Integer.valueOf(1500), bubbleSortedSalaries.get(0));
        assertEquals(Integer.valueOf(2000), bubbleSortedSalaries.get(1));
        assertEquals(Integer.valueOf(3000), bubbleSortedSalaries.get(2));

        reset();
    }

    @Test
    public void getByInsertionSortTest() {
        Payroll unsortedPayroll = new Payroll();
        unsortedPayroll.setItems(3);

        List<Integer> salaries = new ArrayList<Integer>();
        salaries.add(3000);
        salaries.add(2000);
        salaries.add(1500);
        unsortedPayroll.setSalaries(salaries);

        List<Integer> unsortedSalaries = unsortedPayroll.getSalaries();

        ServiceUtils serviceUtils = new ServiceUtils();

        List<Integer> bubbleSortedSalaries = serviceUtils.getByInsertionSort(unsortedSalaries);

        // size n = 1000?
        assertEquals(3, bubbleSortedSalaries.size());

        // salaries comparison
        assertEquals(Integer.valueOf(1500), bubbleSortedSalaries.get(0));
        assertEquals(Integer.valueOf(2000), bubbleSortedSalaries.get(1));
        assertEquals(Integer.valueOf(3000), bubbleSortedSalaries.get(2));

        reset();
    }

    @Test
    public void sortingPerformanceTest() {
        // time comparison
        PayrollService payrollService = new PayrollService();
        ServiceUtils serviceUtils = new ServiceUtils();
        StopWatch stopWatch = new StopWatch();

        // bubble sort
        Payroll unsortedPayrollForBubbleSort = payrollService.getPayroll(1000);
        List<Integer> unsortedSalariesForBubbleSort = unsortedPayrollForBubbleSort.getSalaries();

        stopWatch.start();
        serviceUtils.getByBubbleSort(unsortedSalariesForBubbleSort);
        stopWatch.stop();
        unsortedPayrollForBubbleSort.setSortingTime(stopWatch.getTime(TimeUnit.MILLISECONDS));

        // selection sort
        Payroll unsortedPayrollForSelectionSort = payrollService.getPayroll(1000);
        List<Integer> unsortedSalariesForSelectionSort = unsortedPayrollForSelectionSort.getSalaries();

        stopWatch.reset();

        stopWatch.start();
        serviceUtils.getBySelectionSort(unsortedSalariesForSelectionSort);
        stopWatch.stop();
        unsortedPayrollForSelectionSort.setSortingTime(stopWatch.getTime(TimeUnit.MILLISECONDS));

        // insertion sort
        Payroll unsortedPayrollForInsertionSort = payrollService.getPayroll(1000);
        List<Integer> unsortedSalariesForInsertionSort = unsortedPayrollForInsertionSort.getSalaries();

        stopWatch.reset();

        stopWatch.start();
        serviceUtils.getByInsertionSort(unsortedSalariesForInsertionSort);
        stopWatch.stop();
        unsortedPayrollForInsertionSort.setSortingTime(stopWatch.getTime(TimeUnit.MILLISECONDS));

        // asserts
        assertTrue(unsortedPayrollForBubbleSort.getSortingTime() >= unsortedPayrollForSelectionSort.getSortingTime());
        assertTrue(unsortedPayrollForSelectionSort.getSortingTime() >= unsortedPayrollForInsertionSort.getSortingTime());
    }
}
