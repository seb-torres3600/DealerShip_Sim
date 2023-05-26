import java.util.HashMap;
import java.util.ArrayList;

public class SalesPerson extends Staff{
    public SalesPerson(){
        super();
        this.dailyPay = 500;
    }    

    public SalesPerson(String toBecomeName){
        super(toBecomeName);
        this.dailyPay = 500;
    }

    public Vehicle sell(Buyer b, HashMap<String, ArrayList<Vehicle>> vehicles, VehicleFactory vf){
        //buyers have .want 0.10, 0.40, 0.70; .desiredType "Truck", "Car", "Performance Car"
        //always try to sell most expensive of desiredType (broken ones cannot be sold)
        //chance of sale goes up 10% each for: like-new condition, sparkling cleanliness
        //if no vehicles of desiredType, sell most expensive vehicle remaining at -20% chance
        Vehicle toBeSoldDummy = vf.makeDefaultVehicle(); 
        double sellChance = 0.0;
        double r = Math.random();

        //if the desired type is in stock
        if(!HelperClass.mostExpensive_monotype(vehicles.get(b.desiredType), vf).name.equals("Unknown Name")){ 
            toBeSoldDummy = HelperClass.mostExpensive_monotype(vehicles.get(b.desiredType), vf);
            //calculate the sell chance (range: 0-1)
            sellChance = b.want;
            if(toBeSoldDummy.condition == 2){ sellChance+=0.10; }
            if(toBeSoldDummy.cleanliness == 2){ sellChance+=0.10; }
            //run a random number (0-1) against the sellChance
            if(sellChance >= r){
                return toBeSoldDummy; //if sale succeeds return the Vehicle we sold
            }else{
                return vf.makeDefaultVehicle(); //if sale fails return default Vehicle
            }
        }else{//try to sell most expensive car left
            //if there is one that can be sold...
            if(!HelperClass.mostExpensive_inventory(vehicles, vf).name.equals("Unknown Name")){
                toBeSoldDummy = HelperClass.mostExpensive_inventory(vehicles, vf);
                //we try to sell it
                sellChance = b.want;
                if(toBeSoldDummy.condition == 2){ sellChance+=0.10; }
                if(toBeSoldDummy.cleanliness == 2){ sellChance+=0.10; }
                if(sellChance >= r){
                    //if the sale succeeds, we need to implement the add ons that the buyer requests (initialized with Buyer)

                    //at this point, toBeSoldDummy is the original vehicle
                    
                    if(b.extended_warranty){toBeSoldDummy = new ExtendedWarrantyVehicle(toBeSoldDummy);}
                    if(b.sat_radio){toBeSoldDummy = new SatelliteRadioVehicle(toBeSoldDummy);}
                    if(b.undercoating){toBeSoldDummy = new UndercoatingVehicle(toBeSoldDummy);}
                    if(b.road_rescue){toBeSoldDummy = new RoadRescueCoverageVehicle(toBeSoldDummy);}
                    
                    return toBeSoldDummy; //if sale succeeds return the Vehicle we sold
                }else{
                    return vf.makeDefaultVehicle(); //if sale fails return default Vehicle 
                }
            }
        }
        //if the above Logic fails return default Vehicle
        return vf.makeDefaultVehicle(); 
    }
}
