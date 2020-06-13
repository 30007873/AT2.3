package payroll.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import payroll.domain.Payroll;

public class PayrollServiceTest {

    private static final Logger log = Logger.getLogger(PayrollServiceTest.class.getName());

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
        log.info("Running JUnit test cases from class: " + PayrollServiceTest.class);

    }

    @AfterClass
    public static void after() {
        log.info("Testing class " + PayrollServiceTest.class + " has completed.");

    }

    public void reset() {
        tearDown();
        setup();
    }

    /**
     * This method is used to test getPayroll, accepts nothing, returns nothing and does not throw any exception
     */
    @Test
    public void getPayrollTest() {
        // create payroll service
        PayrollService payrollService = new PayrollService();
        // get payroll
        Payroll payroll = payrollService.getPayroll(3);
        // asserts
        assertEquals(3, payroll.getItems());
        assertTrue(payroll.getSalaries().get(0) >= 1);
        assertTrue(payroll.getSalaries().get(1) >= 1);
        assertTrue(payroll.getSalaries().get(2) >= 1);

        reset();
    }

    /**
     * This method is used to test getSortedPayroll, accepts nothing, returns nothing and does not throw any exeption
     */
    @Test
    public void getSortedPayrollTest() {
        // create payroll service
        PayrollService payrollService = new PayrollService();
        // get payroll
        Payroll unsortedPayroll = payrollService.getPayroll(3);
        // get bubble sorted payroll
        Payroll sortedPayroll = payrollService.getSortedPayroll(unsortedPayroll, "bubble");
        // get salaries
        List<Integer> sortedPayrollSalaries = sortedPayroll.getSalaries();
        // asserts
        assertTrue(sortedPayrollSalaries.get(0) <= sortedPayrollSalaries.get(1));
        assertTrue(sortedPayrollSalaries.get(0) <= sortedPayrollSalaries.get(2));
        // get selection sorted payroll
        sortedPayroll = payrollService.getSortedPayroll(unsortedPayroll, "selection");
        // get salaries
        sortedPayrollSalaries = sortedPayroll.getSalaries();
        // asserts
        assertTrue(sortedPayrollSalaries.get(0) <= sortedPayrollSalaries.get(1));
        assertTrue(sortedPayrollSalaries.get(0) <= sortedPayrollSalaries.get(2));
        // get insertion sorted payroll
        sortedPayroll = payrollService.getSortedPayroll(unsortedPayroll, "insertion");
        // get payroll salaries
        sortedPayrollSalaries = sortedPayroll.getSalaries();
        // asserts
        assertTrue(sortedPayrollSalaries.get(0) <= sortedPayrollSalaries.get(1));
        assertTrue(sortedPayrollSalaries.get(0) <= sortedPayrollSalaries.get(2));

        reset();
    }
}
