import java.util.ArrayList;

public class Buyer {
    public String desiredType;
    public Double want;
    // Add if the buyer gets the add-on packages 
    public Boolean extended_warranty = (Math.random() < 0.25);
    public Boolean undercoating = (Math.random() < 0.10);
    public Boolean road_rescue = (Math.random() < 0.05);
    public Boolean sat_radio = (Math.random() < 0.40);

    public Buyer(){
        // Array for how much they want to buy a car
        ArrayList<Double> chance = new ArrayList<Double>();
        // Array for what type of car they want
        ArrayList<String> car_wanted = new ArrayList<String>();
        
        // Add the chance they will buy a car to the list 
        chance.add(.10);
        chance.add(.40);
        chance.add(.70);
        // Add vehicle types to list to pick from
        car_wanted.add("Truck");
        car_wanted.add("Car");
        car_wanted.add("Performance Car");
        car_wanted.add("Monster Truck");
        car_wanted.add("Electric Car");
        car_wanted.add("Motorcycle");
        car_wanted.add("Super Car");
        car_wanted.add("Muscle Car");
        car_wanted.add("Classic Car");
        
        // Randomly get a value from our arrays for our buyer
        want = chance.get(HelperClass.CreateRandomNumber(0,chance.size()-1));
        desiredType = car_wanted.get(HelperClass.CreateRandomNumber(0,car_wanted.size()-1));
    }
}
