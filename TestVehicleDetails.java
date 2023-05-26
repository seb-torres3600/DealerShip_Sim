import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestVehicleDetails{
    VehicleFactory vf = new VehicleFactory();
    MuscleCar muscle_one = vf.makeMuscleCar("test");
    Motorcycle motorcycle_one = vf.makeMotorcycle("test");
    

    @Test
    public void testFixBonus(){
        String fail = "FAIL: testFixBonus()";
        assertEquals(fail, muscle_one.fix_bonus,400);
        System.out.println("SUCCESSFUL: testFixBonus()" );
    }

    @Test
    public void testdefaultVehicle(){
        String fail = "FAIL: testdefaultVehicle()";
        Vehicle test_v = vf.makeDefaultVehicle();
        assertEquals(fail, test_v.type,"DEFAULT");
        System.out.println("SUCCESSFUL: testdefaultVehicle()" );
    }

    @Test
    public void testSalePrice(){
        String fail = "FAIL: testSalePrice()";
        assertNotEquals(fail, motorcycle_one.purchase_price*2, (int)motorcycle_one.sell_price);
        System.out.println("SUCCESSFUL: testSalePrice()" );
    }

    @Test
    public void testCleanliness(){
        String fail = "FAIL: testCleanliness()";
        assertEquals(fail, muscle_one.fix_bonus,400);
        System.out.println("SUCCESSFUL: testCleanliness()" );
    }

    @Test
    public void testSellBonus(){
        String fail = "FAIL: testSellBonus()";
        SuperCar def_supercar = vf.makeSuperCar("test");
        assertEquals(fail, def_supercar.sell_bonus, 5000);
        System.out.println("SUCESSFUL: testSellBonus()");
    }


}