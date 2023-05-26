import java.util.Scanner;

public class BuyVehicle implements Command{
    private FNCD dealer;
    private Scanner input;

    public BuyVehicle(FNCD dealership, Scanner in){
        this.dealer = dealership;
        this.input = in;
    }   

    @Override
    public void execute(){
        dealer.BuyVehicle(input);
    }

}