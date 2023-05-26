import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Scanner;

import java.io.IOException;
import java.io.FileWriter;

public class HelperClass {
    
    // Function to generate random number between ranges
    public static int CreateRandomNumber(int range_min, int range_max){
        int max = range_max;
        int min = range_min;
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }

    // Find most expensive vehicle of a certain type
    public static Vehicle mostExpensive_monotype(ArrayList<Vehicle> list, VehicleFactory vf){
        Vehicle mostExpensive = vf.makeDefaultVehicle(); 
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).sell_price >= mostExpensive.sell_price && list.get(i).condition > 0){
                mostExpensive = list.get(i);
            }
        }
        return mostExpensive;
    }

    // Find most expensive vehicle in inventory
    public static Vehicle mostExpensive_inventory(HashMap<String, ArrayList<Vehicle>> inventory, VehicleFactory vf){
        ArrayList<Vehicle> mostExpensives = new ArrayList<Vehicle>();
        mostExpensives.add(mostExpensive_monotype(inventory.get("Car"), vf));
        mostExpensives.add(mostExpensive_monotype(inventory.get("Truck"), vf));
        mostExpensives.add(mostExpensive_monotype(inventory.get("Performance Car"), vf));

        return mostExpensive_monotype(mostExpensives, vf);
    }

    // Returns random vehicle type so we can pick car to clean or fix from hashmap
    public static String getRandomVehicleType(){
        ArrayList<String> vehicle_names = new ArrayList<String>();
        vehicle_names.add("Truck");
        vehicle_names.add("Car");
        vehicle_names.add("Performance Car");
        vehicle_names.add("Monster Truck");
        vehicle_names.add("Electric Car");
        vehicle_names.add("Motorcycle");
        vehicle_names.add("Super Car");
        vehicle_names.add("Muscle Car");
        vehicle_names.add("Classic Car");

        String rand_vehicle = vehicle_names.get(CreateRandomNumber(0, vehicle_names.size()-1));
        return rand_vehicle;
    }

    // Mechanic conditions are set at integers
    // Returns a string based on integer condition for printing purposes
    public static String getConditionString(Integer cond){
        String condition_string = "Unknown";
        switch (cond){
            case 0:
                condition_string = "Broken";
                break;
            case 1:
                condition_string = "Used";
                break;
            case 2:
                condition_string = "Like New";
                break;
        }
        return condition_string;
    }

    // gets arraylist of vehicles by cleanliness level desired
    public static ArrayList<Vehicle> getVehiclesByCleanliness(int cDesired, ArrayList<Vehicle> list){
        ArrayList<Vehicle> ret = new ArrayList<Vehicle>();
        for (Vehicle v: list){
            if( v.cleanliness == cDesired){ ret.add(v); }
        }
        return ret;
    }

    public static Boolean wtf(FileWriter fw, String s){ 
        //this is shorthand for "writeToFile", not the common expletive term
        try{
            fw.write(s+"\n");
            return true;
        } catch (IOException e){
            return false;
        }
    }
    // Convert integer representing condition to a string for printing 
    public static String COtoStr(int cond){
        switch (cond){
            case 0:
                return "Broken";
            case 1:
                return "Used";
            case 2:
                return "Like-new";
        }
        return "COtoStr helper method was given: "+cond;
    }

    // Convert integer for cleanliness to a string for printing purposes
    public static String CLtoStr(int cle){
        switch (cle){
            case 0:
                return "Dirty";
            case 1:
                return "Clean";
            case 2:
                return "Sparkling";
        }
        return "CLtoStr helper method was given: "+cle;
    }

    // Get random number from truncated normal distribution
    // https://stackoverflow.com/questions/31754209/can-random-nextgaussian-sample-values-from-a-distribution-with-different-mean
    // https://piazza.com/class/lctkfkhd6uehn/post/110
    public static int engine_size(){
        Random r = new Random();
        double mySample = r.nextGaussian(700, 300);
        while(mySample < 50){
            mySample = r.nextGaussian(700, 300);
        }
        return (int)mySample;
    }

    public static String getMonsterTruckName(ArrayList<Vehicle> monster_trucks, ArrayList<Vehicle> sold_mt){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Avenger");
        names.add("Batman");
        names.add("Backwards Bob");
        names.add("Bear Foot");
        names.add("Bigfoot");
        names.add("Black Stallion");
        names.add("Blacksmith");
        names.add("Blue Thunder");
        names.add("Brutus");
        names.add("Bulldozer");
        names.add("Cyborg");
        names.add("El Toro Loco");
        names.add("Game Over");
        names.add("Grinder");
        names.add("Gunslinger");
        names.add("Jurrasic Attack");
        names.add("Grave Digger");
        names.add("Gunslinger");
        String truck_name = names.get(CreateRandomNumber(0,  names.size()-1));
        Integer name_count = 0;
        for(Vehicle n: monster_trucks){
            if(n.name.contains(truck_name)){
                name_count += 1;
            }
        }
        for(Vehicle n: sold_mt){
            if(n.name.contains(truck_name)){
                name_count += 1;
            }
        }
        truck_name = truck_name + "_" + name_count.toString();
        return truck_name;
    }

    public static int getNumRaceable(ArrayList<Vehicle> list){
        int raceable = 0;
        for(Vehicle v: list){
            if(v.condition > 0){
                raceable++;
            }
        }
        return raceable;
    }

    public static ArrayList<Vehicle> getRaceableVehicles(ArrayList<Vehicle> vehs){ //returns all vehicles in a given list that are not Broken
        ArrayList<Vehicle> ret = new ArrayList<Vehicle>();
        for(Vehicle v: vehs){
            if(v.condition > 0){
                ret.add(v);
            }
        }
        return ret;
    }

    public static ArrayList<VehicleDriverTuple> matchDriversToVehicles(int numPairs, ArrayList<Vehicle> vehicles, ArrayList<Driver> drivers, VehicleFactory vf){
        ArrayList<VehicleDriverTuple> retList = new ArrayList<VehicleDriverTuple>();
        Vehicle tempV = vf.makeDefaultVehicle(); 
        Driver tempD = new Driver(); 
        ArrayList<Vehicle> raceables = getRaceableVehicles(vehicles);
        //numPairs is expected to be <= number raceable vehicles 
        if(numPairs >= 3){ //for 3 race pairs
            // we sometimes had more available cars to drive than drivers so indexed out
            for(int i = 0; i < drivers.size(); i++){
                tempD = drivers.get(i);
                tempV = raceables.get(i);
                retList.add(new VehicleDriverTuple(tempV, tempD));
            }
        } else if(numPairs == 2){ //for 2 race pairs
            for(int i = 0; i < numPairs; i++){
                tempD = drivers.get(i);
                tempV = raceables.get(i);
                retList.add(new VehicleDriverTuple(tempV, tempD));
            }
        } else if(numPairs == 1){ //for 1 race pair
            retList.add(new VehicleDriverTuple(raceables.get(0), drivers.get(0)));
        } else{//for no race pairs
            //dont add anything
        }
        return retList;
    }

    public static String getWashTypeString(int type){ //for .txt purposes
        String wash_type = "";
        switch(type){
            case 1:
                wash_type = "Elbow Grease Wash";
                break;
            case 2:
                wash_type = "Chemical Wash";
                break;
            case 3:
                wash_type = "Detailed Wash";
                break;
        }
        return wash_type;
    }

    public static Vehicle getVehicleByName(HashMap<String, ArrayList<Vehicle>> inventory, String v_name){
        VehicleFactory vf = new VehicleFactory();
        Vehicle veh = vf.makeDefaultVehicle();
        for(Entry<String, ArrayList<Vehicle>> entry: inventory.entrySet()) {
            for(Vehicle v: entry.getValue()){
                if(v.name.equals(v_name)){
                    return v;
                }
            }
        }
        return veh;
    }

    public static FNCD SwithDealer(FNCD north, FNCD south, FNCD current){
        if(current == north){
            return south;
        }
        else{
            return north;
        }
    }

    public static Vehicle ExtraInfoNeeded(HashMap<String, ArrayList<Vehicle>> inventory, Scanner in){
        System.out.print("Vehicle Name: ");
        String v_name = "DEFAULTVEHICLE";
        Vehicle v = new DefaultVehicle();
        while(v_name == "DEFAULTVEHICLE"){
            v_name = in.nextLine();
            v = HelperClass.getVehicleByName(inventory,v_name);
            v_name = v.name;
            if(v.name == "DEFAULTVEHICLE"){
                System.out.println("No vehicle with that name, pick another");
            }
        }
        return v;
    }
    public static ManualBuyerQuad AddOnDescisions(Scanner in){
        ArrayList<Boolean> addon_bool = new ArrayList<Boolean>();
        ArrayList<String> addon_string = new ArrayList<String>();

        addon_bool.add(false);
        addon_bool.add(false);
        addon_bool.add(false);
        addon_bool.add(false);

        addon_string.add("Extended Warranty");
        addon_string.add("Satellite Radio");
        addon_string.add("Undercoating");
        addon_string.add("Road Rescue");

        System.out.println("Please Answer the following with yes or no");
        for(int i = 0; i < addon_string.size();i++){
            System.out.print(addon_string.get(i)+"? ");
            String ans = in.nextLine().toLowerCase();
            if(ans.equals("yes")){
                addon_bool.set(i, true);
            }
        }
        Boolean extended_warranty = addon_bool.get(0);
        Boolean sat_radio = addon_bool.get(1);
        Boolean undercoating = addon_bool.get(2);
        Boolean road_rescue = addon_bool.get(3);

        ManualBuyerQuad add = new ManualBuyerQuad(extended_warranty, sat_radio, undercoating, road_rescue);
        return add;
    }

    public static void printTable(FNCD dea){
        System.out.println("");
        System.out.println("--------------------------------- COMMANDS TABLE -------------------------------");
        System.out.println("---------------------------------- " + dea.dealer_name.replace(":", "")+"--------------------------------");
        System.out.println("Type in the number of the command you wish to use ");
        System.out.println("1: Get your salespersons name");
        System.out.println("2: Ask salesperson the current time");
        System.out.println("3: Ask for a different Salesperson");
        System.out.println("4: View current inventory");
        System.out.println("5: Ask detail on specific Vehicle");
        System.out.println("6: Buy specific Vehicle");
        System.out.println("7: Switch Dealer");
        System.out.println("8: Exit Manual Entry");
        System.out.println("--------------------------------- COMMANDS TABLE -------------------------------");
        System.out.println("");
    }

    public static void simulate(FNCD one, FNCD two, int days){
        HelperClass.wtf(one.fWriter, "------------------------------------- BEGIN SIMULATION ----------------------------------------------");
        int weeks = 0;
        int dayOfWeek = 1;
        two.fWriter = one.fWriter;

        for(int i = 1; i <= days; i++){
            weeks = i/7;
            one.logr = Logger.getLoggerInstance(i);
            two.logr = Logger.getLoggerInstance(i);
            //all the daily activities mentioned in the PDF -- we may want to create private functions for these 
            //open
            HelperClass.wtf(one.fWriter, "*** FNCD Day " + i + " ***");
            //OBSERVER PATTERN
            one.notifyObservers("*** FNCD Day " + i + " ***");

            one.openDealership(i);
            two.openDealership(i);
            //washing
            one.doWashing();
            two.doWashing();
            //repairing
            one.doRepairs();
            two.doRepairs();
            //selling
            if(i == days){
                day31(one, two);
            } 
            else{
                one.doSelling();
                two.doSelling();
            }
            // if the day is a race day, then race
            dayOfWeek = i - (weeks*7);
            if(dayOfWeek%3 == 0 || dayOfWeek%7 == 0 ){
                one.doRacing();
                two.doRacing();
            }
            //ending
            one.endDay();
            two.endDay();

            one.doQuitting();
            two.doQuitting();

            one.printTabular();
            two.printTabular();
    
            one.logr.closeFW();
            one.trakr.print(one);
            two.trakr.print(two);
        }
        //announce end of simulation 
        HelperClass.wtf(one.fWriter, "------------------------------------- END OF SIMULATION ----------------------------------------------\n");
        // close tracker.txt file
        one.trakr.closeFW();
        
        try{
            one.fWriter.close();
        } catch (IOException e){
            System.out.println("Error closing FileWriter.");
        }
        System.out.println("Done.");
    }

    public static void day31(FNCD one, FNCD two){
        FNCD dealership = one;
        // Assign sales person for each dealer
        one.AssignSalesPerson();
        two.AssignSalesPerson();
        // scan stdin
        try (Scanner in = new Scanner(System.in)) {
            // make controller 
          Controller cnt = new Controller();
          // Loop until we exit
          while(true){
            // Print table everytime, maybe change it? 
            HelperClass.printTable(dealership);
            System.out.print("Please enter your command: ");
            // get next line 
            String comd = in.nextLine();
            // swith dealer when command is 7
            if(comd.equals("7")){
                dealership = HelperClass.SwithDealer(one, two, dealership);
                continue;
            }
            if(comd.equals("8")){
                return;
            }
            // set command
            cnt.setCommand(comd, dealership,in);
            // execute commmand
            cnt.executeCommand();
          }
        }
    }
}

