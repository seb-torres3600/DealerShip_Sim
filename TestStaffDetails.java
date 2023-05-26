import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestStaffDetails{
    StaffFactory sf = new StaffFactory();

    @Test
    public void testIntern(){
        Intern test_intern = sf.makeIntern("test_1");
        String fail = "FAILURE: testIntern()";
        assertEquals(fail,test_intern.dailyPay, 100);
        System.out.println("SUCCESSFUL: testIntern()");
    }

    @Test
    public void testDriver(){
        Driver test_driver = sf.makeDriver("driver_1");
        String fail = "FAILURE: testDriver()";
        assertEquals(fail,test_driver.dailyPay, 1000);
        System.out.println("SUCCESSFUL: testDriver()");
    }

    @Test
    public void testSalePerson(){
        SalesPerson test_sales = sf.makeSalesPerson("sales_1");
        String fail = "FAILURE: testSalesPerson()";
        assertEquals(fail, test_sales.dailyPay, 500);
        System.out.println("SUCCESSFUL: testSalePerson()");
    }

    @Test
    public void testMechanic(){
        Mechanic test_sales = sf.makeMechanic("mech_1");
        String fail = "FAILURE: testMechanic()";
        assertEquals(fail, test_sales.dailyPay, 500);
        System.out.println("SUCCESSFUL: testMechanic()");
    }

}