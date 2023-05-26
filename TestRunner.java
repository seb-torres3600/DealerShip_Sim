import org.junit.runner.JUnitCore;

public class TestRunner{
    public static void main(String[] args) {

        JUnitCore.runClasses(TestVehicleDetails.class);
        JUnitCore.runClasses(TestFNCDDetails.class);
        JUnitCore.runClasses(TestStaffDetails.class);
        JUnitCore.runClasses(TestBuyerDetails.class);
        
     }
}