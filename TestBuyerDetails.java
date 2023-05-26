import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class TestBuyerDetails{
    
    @Test
    public void testBuyer(){
        String fail = "FAIL: testBuyer()";
        Buyer test_buyer = new Buyer();
        assertNotEquals(fail,test_buyer.want,null);
        assertNotEquals(fail,test_buyer.desiredType,null);
        System.out.println("SUCCESSFUL: testBuyer()");
        
    }
}