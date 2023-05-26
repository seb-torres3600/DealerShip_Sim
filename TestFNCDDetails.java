import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;

public class TestFNCDDetails{
    
    FNCD fncd = new FNCD(null, null); 
    @Test
    public void testVehicleAmount(){
        String fail = "FAIL: testVehicleAmount()";
        // 9 types of vehicles 
        assertEquals(fail, fncd.inventory.size(),9);
        System.out.println("SUCCESSFUL: testVehicleAmount()");
    }

    @Test
    public void testInitVehicleAmounts(){
        String fail = "FAIL: testInitVehicleAmounts()";
        int total_vehicles = 0;
        for(Entry<String, ArrayList<Vehicle>> entry: fncd.inventory.entrySet()) {
            total_vehicles += entry.getValue().size();
        }
        // 9 kind of vehicles * 6 init vehicles = 54
        assertEquals(fail, total_vehicles,54);
        System.out.println("SUCCESSFUL: testInitVehicleAmounts()");
    }

    @Test 
    public void testInitStaffAmounts(){
        String fail = "FAIL: testInitStaffAmounts()";
        int total_staff = 0;
        HashMap <String, Integer> emp_count = fncd.getStaff();
        for(Entry<String, Integer> entry: emp_count.entrySet()) {
            total_staff += entry.getValue();
        }
        // 4 kind of staff * 3 init staff= 12
        assertEquals(fail, total_staff,12);
        System.out.println("SUCCESSFUL: testInitStaffAmounts()");
    }

    @Test
    public void testFileWriter(){
        String fail = "FAIL: testFileWriter()";
        assertNotEquals(fail, fncd.fWriter,null);
        System.out.println("SUCCESSFUL: testFileWriter()");
    }

    @Test
    public void testObservers(){
        String fail = "FAIL: testFileWriter()";
        assertEquals(fail, fncd.getObservers().size(),2);
        System.out.println("SUCCESSFUL: testFileWriter()");
    }

}