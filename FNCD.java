import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FNCD implements Subject{ //class definition

    // I had to look up how to write to Files: https://www.w3schools.com/java/java_files_create.asp
    private File outputFile;
    public FileWriter fWriter;
    private String outputFileName;
    public Tracker trakr;
    public Logger logr;
    private ArrayList<Observer> observers;
    private int operating_budget;
    private int bail_out_total;
    private ArrayList<Intern> interns;
    private ArrayList<SalesPerson> sales_persons;
    private ArrayList<Mechanic> mechanics;
    private ArrayList<Driver> drivers;
    private HashMap<String, Integer> employee_count;
    private HashMap<String, ArrayList<Staff>> departed_staff;
    private ArrayList<Buyer> customers;
    public HashMap<String, ArrayList<Vehicle>> sold_vehicles;
    public HashMap<String, ArrayList<Vehicle>> inventory;
    private int daySalesTotal;
    private int totalPromotedInterns;
    private VehicleFactory vf;
    private StaffFactory sf;
    private SalesPerson currentSalesPerson;
    public String dealer_name;

    // initiate our values to create a dealer
    public FNCD(String toBecomeFileName, String name){ //constructor with output File parameter
        dealer_name = name;

        //initialize the output file
        outputFileName = toBecomeFileName+".txt";
        
        outputFile = new File(outputFileName);

        try{
            if(outputFile.createNewFile()){
                System.out.println("Output file created: "+outputFile.getName());
            } else {
                System.out.println("File already exists: "+outputFile.getName());
            }
        } catch(IOException e){
            System.out.println("An error occurred creating the output text file.");
        }
        //initialize the FileWriter to use
        try{
            fWriter = new FileWriter(outputFileName);
            System.out.println("Initialized the FileWriter.");
        } catch (IOException e){
            System.out.println("An error occurred initializing the FileWriter.");
        }

        //declare factories for use (FACTORY PATTERN)
        vf = new VehicleFactory();
        sf = new StaffFactory();

        operating_budget = 500000;
        bail_out_total = 0;
        inventory = new HashMap<String, ArrayList<Vehicle>>();
        sold_vehicles = new HashMap<String, ArrayList<Vehicle>>();
        interns = new ArrayList<Intern>();
        sales_persons = new ArrayList<SalesPerson>();
        mechanics = new ArrayList<Mechanic>();
        drivers = new ArrayList<Driver>();
        employee_count = new HashMap<String, Integer>();
        departed_staff = new HashMap<String, ArrayList<Staff>>();
        daySalesTotal = 0;
        totalPromotedInterns = 0;
        observers = new ArrayList<Observer>();

        // We have 3 of each employee
        employee_count.put("Intern", 3);
        employee_count.put("Sales Person", 3);
        employee_count.put("Mechanic", 3);
        employee_count.put("Driver", 3);

        // Initialize our departed staff as empty since no one has quit yet
        ArrayList <Staff> departed_interns = new ArrayList<Staff>();
        departed_staff.put("Intern", departed_interns);
        ArrayList <Staff> departed_sales = new ArrayList<Staff>();
        departed_staff.put("Sales Person", departed_sales);
        ArrayList <Staff> departed_mech = new ArrayList<Staff>();
        departed_staff.put("Mechanic", departed_mech);
        ArrayList <Staff> departed_drivers = new ArrayList<Staff>();
        departed_staff.put("Driver", departed_drivers); 

        // Initialize our inventory hashmap
        ArrayList<Vehicle> trucks = new ArrayList<Vehicle>();
        inventory.put("Truck", trucks);
        ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
        inventory.put("Car", cars);
        ArrayList<Vehicle> performance = new ArrayList<Vehicle>();
        inventory.put("Performance Car", performance);
        ArrayList<Vehicle> electric = new ArrayList<Vehicle>();
        inventory.put("Electric Car", electric);
        ArrayList<Vehicle> motorcycle = new ArrayList<Vehicle>();
        inventory.put("Motorcycle", motorcycle);
        ArrayList<Vehicle> monster = new ArrayList<Vehicle>();
        inventory.put("Monster Truck", monster);
        ArrayList<Vehicle> super_car = new ArrayList<Vehicle>();
        inventory.put("Super Car", super_car);
        ArrayList<Vehicle> muscle_car = new ArrayList<Vehicle>();
        inventory.put("Muscle Car", muscle_car);
        ArrayList<Vehicle> classic_car = new ArrayList<Vehicle>();
        inventory.put("Classic Car", classic_car);


        // Initialize our sold hashmap
        ArrayList<Vehicle> sold_trucks = new ArrayList<Vehicle>();
        sold_vehicles.put("Truck", sold_trucks);
        ArrayList<Vehicle> sold_cars = new ArrayList<Vehicle>();
        sold_vehicles.put("Car", sold_cars);
        ArrayList<Vehicle> sold_performance = new ArrayList<Vehicle>();
        sold_vehicles.put("Performance Car", sold_performance);
        ArrayList<Vehicle> sold_electic = new ArrayList<Vehicle>();
        sold_vehicles.put("Electric Car", sold_electic);
        ArrayList<Vehicle> sold_motorcycle = new ArrayList<Vehicle>();
        sold_vehicles.put("Motorcycle", sold_motorcycle);
        ArrayList<Vehicle> sold_monster = new ArrayList<Vehicle>();
        sold_vehicles.put("Monster Truck", sold_monster);
        ArrayList<Vehicle> sold_super_car = new ArrayList<Vehicle>();
        sold_vehicles.put("Super Car", sold_super_car);
        ArrayList<Vehicle> sold_muscle_car = new ArrayList<Vehicle>();
        sold_vehicles.put("Muscle Car", sold_muscle_car);
        ArrayList<Vehicle> sold_classic_car = new ArrayList<Vehicle>();
        sold_vehicles.put("Classic Car", sold_classic_car);

        // Add 6 of each type of car to our inventory
        for(Integer i = 1; i < 7; i++){
            String car_num = i.toString();

            Car tmp_car = vf.makeCar("car_" + car_num);
            Truck tmp_truck = vf.makeTruck("truck_"+ car_num);
            PerformanceCar tmp_performance = vf.makePerformanceCar("performance_"+car_num);
            ElectricCar tmp_electric = vf.makeElectricCar("electric_"+car_num);
            Motorcycle tmp_motorcycle = vf.makeMotorcycle("motorycle_"+car_num);
            String truck_name = HelperClass.getMonsterTruckName(inventory.get("Monster Truck"),sold_vehicles.get("Monster Truck"));
            MonsterTruck tmp_monster = vf.makeMonsterTruck(truck_name);
            SuperCar tmp_super = vf.makeSuperCar("super_car"+car_num);
            MuscleCar tmp_muscle = vf.makeMuscleCar("muscle_car"+car_num);
            ClassicCar tmp_classic = vf.makeClassicCar("classic_car_"+car_num);

            inventory.get("Truck").add(tmp_truck);
            inventory.get("Car").add(tmp_car);
            inventory.get("Performance Car").add(tmp_performance);
            inventory.get("Electric Car").add(tmp_electric);
            inventory.get("Motorcycle").add(tmp_motorcycle);
            inventory.get("Monster Truck").add(tmp_monster);
            inventory.get("Super Car").add(tmp_super);
            inventory.get("Muscle Car").add(tmp_muscle);
            inventory.get("Classic Car").add(tmp_classic);

            // Remove purchase price from operating budget
            operating_budget -= tmp_car.purchase_price;
            operating_budget -= tmp_performance.purchase_price;
            operating_budget -= tmp_truck.purchase_price;
            operating_budget -= tmp_electric.purchase_price;
            operating_budget -= tmp_motorcycle.purchase_price;
            operating_budget -= tmp_monster.purchase_price;
            operating_budget -= tmp_super.purchase_price;
            operating_budget -= tmp_muscle.purchase_price;
            operating_budget -= tmp_classic.purchase_price;
        }

        // Add 3 of each staff type to our starting staff
        for(Integer i = 1; i < 4; i++){
            String employee_num = i.toString();
            Intern tmp_intern = sf.makeIntern("intern_" + employee_num);
            Mechanic tmp_mech = sf.makeMechanic("mechanic_" + employee_num);
            SalesPerson tmp_salesperson = sf.makeSalesPerson("salesperson_"+employee_num);
            Driver tempDriver = sf.makeDriver("driver_"+employee_num);

            // Add staff to their own arraylist
            interns.add(tmp_intern);
            mechanics.add(tmp_mech);
            sales_persons.add(tmp_salesperson);
            drivers.add(tempDriver);
        }
    
        //init Tracker
        // START OF OUR OBSERVER PATTERN
        trakr = Tracker.getTrackerInstance();
        logr = Logger.getLoggerInstance(1);
        
        observers.add(trakr);
        observers.add(logr);
    }

    public void notifyObservers(String msg){
        logr.update(msg);
        trakr.update(msg);
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void payBonus(int amount, Staff emp){ //pay an employee from budget
        //decrement operating budget and pay the employee
        operating_budget -= amount;
        checkBudget();
        emp.getBonus(amount);
    }

    public void paySalary(Staff emp){ //specified employee gets their daily pay from the budget
        //decrement op. budget as employee gets paid
        operating_budget -= emp.dailyPay;
        checkBudget();
        emp.getSalary(); 
    }

    public HashMap<String, Integer> getStaff(){
        return employee_count;
    }

    public ArrayList<Observer> getObservers(){
        return observers;
    }

    // Assing new sales person for commands
    public void AssignSalesPerson(){
        if(currentSalesPerson == null){
            currentSalesPerson = sales_persons.get(HelperClass.CreateRandomNumber(0, sales_persons.size()-1));
        }
    }
    // Get current sales person name for commands
    public void GetName(){
        System.out.println("Your Current Sales Person is: " + currentSalesPerson.name);
    }

    // Get time for commands
    public void GetTime(){
        DateTimeFormatter pformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current date and time: "+pformat.format(now));
    }
    // Switch sales person
    public void SwitchSalesPerson(){
        SalesPerson new_person = currentSalesPerson;
        while(new_person == currentSalesPerson){
            int rand_sales = HelperClass.CreateRandomNumber(0, sales_persons.size()-1);
            new_person = sales_persons.get(rand_sales);
        }
        System.out.println("Switching sales person");
        System.out.println("Your new sales person: "+new_person.name);
        currentSalesPerson = new_person;
    }

    // Print inventory, just name and price
    public void GetInventory(){
        System.out.println("--------------Invetory---------------");
        Object[] temp;
        temp = new Object[] {"Name", "Price"};
        System.out.println(String.format("%-22s%-22s",temp));
        System.out.println("-------------------------------------");
        for(Entry<String, ArrayList<Vehicle>> entry: inventory.entrySet()) {
            for(Vehicle v: entry.getValue()){
                // Fix formatting later
                // String cond = HelperClass.COtoStr(v.condition);
                // String clean = HelperClass.CLtoStr(v.cleanliness);
                temp = new Object[] {v.name,"$"+(int)v.sell_price};
                System.out.println(String.format("%-22s%-22s",temp));
            }
        }
    }

    // Print tabular all details of a car
    public void GetDetailsOnItem(Scanner in){
        Vehicle v = HelperClass.ExtraInfoNeeded(inventory,in);
        Object[] temp;
        if(v.type == "Motorcycle"){
            temp = new Object[] {"Name","Type","Purchase Price", "Sell Price", "Condition", "Cleanliness", "Races Won", "Engine Size"};
            System.out.println(String.format("%-22s%-22s%-22s%-22s%-22s%-22s",temp));
            temp = new Object[] {v.name,v.type, v.purchase_price, (int)v.sell_price, HelperClass.COtoStr(v.condition), HelperClass.CLtoStr(v.cleanliness), v.races_won, v.engine_size};
            System.out.println(String.format("%-22s%-22s%-22s%-22s%-22s%-22s",temp));
        }
        else if(v.type == "Electric Car"){
            temp = new Object[] {"Name","Type", "Purchase Price", "Sell Price", "Condition", "Cleanliness", "Races Won", "Car Range"};
            System.out.println(String.format("%-22s%-22s%-22s%-22s%-22s%-22s",temp));
            temp = new Object[] {v.name,v.type, v.purchase_price, (int)v.sell_price, HelperClass.COtoStr(v.condition), HelperClass.CLtoStr(v.cleanliness), v.races_won, v.range};
            System.out.println(String.format("%-22s%-22s%-22s%-22s%-22s%-22s",temp));
        }
        else{
            temp = new Object[] {"Name", "Vehicle Type", "Purchase Price", "Sell Price", "Condition", "Cleanliness", "Races Won"};
            System.out.println(String.format("%-22s%-22s%-22s%-22s%-22s",temp));
            temp = new Object[] {v.name,v.type, v.purchase_price, (int)v.sell_price, HelperClass.COtoStr(v.condition), HelperClass.CLtoStr(v.cleanliness), v.races_won};
            System.out.println(String.format("%-22s%-22s%-22s%-22s%-22s",temp));
        }
    } 

    // Process to buy a vehicle manually 
    public void BuyVehicle(Scanner in){
        if(currentSalesPerson == null){
            currentSalesPerson = sales_persons.get(HelperClass.CreateRandomNumber(0, sales_persons.size()-1));
        }
        Vehicle v = HelperClass.ExtraInfoNeeded(inventory, in);
        ManualBuyerQuad additions = HelperClass.AddOnDescisions(in);
        if(additions.extended_warranty){
            v = new ExtendedWarrantyVehicle(v);
            System.out.println("Added Extended Warranty on Vehicle");
        }
        if(additions.sat_radio){
            v = new SatelliteRadioVehicle(v);
            System.out.println("Added Satellite Radio on Vehicle");
        }
        if(additions.undercoating){
            v = new UndercoatingVehicle(v);
            System.out.println("Added Undercoating on Vehicle");
        }
        if(additions.road_rescue){
            v = new RoadRescueCoverageVehicle(v);
            System.out.println("Added Road Rescue on Vehicle");
        }
        System.out.println("Sold Vehicle: "+ v.name + " for "+ (int)v.sell_price);
        inventory.get(v.type).remove(v);
    }

    // Hire new employees when needed
    public void hireEmployee(String type){ //creates new employee of type (naming convention handled here)
        switch(type){
            // interns get hired from scratch
            case "Intern":
                // Increments to keep track of total interns promoted
                totalPromotedInterns += 1;
                // make sure it's a unique name by adding total interns had to name
                Integer total_interns = departed_staff.get("Intern").size() + employee_count.get("Intern") + totalPromotedInterns + 1;
                // new intern object

                Intern new_intern = sf.makeIntern("intern_"+total_interns);

                // increment hashmap count for intern total
                employee_count.merge("Intern", 1, Integer::sum);
                interns.add(new_intern);
                HelperClass.wtf(fWriter, dealer_name + "Hired Intern "+ new_intern.name);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Hired Intern "+ new_intern.name);
                break;
            // Promote intern for new sales person and mechanics
            case "Sales Person":
                promote("Sales Person");
                break;

            case "Mechanic":
                promote("Mechanic");
                break;
            // hire new driver, not promoting interns 
            case "Driver":
                Integer total_drivers = departed_staff.get("Driver").size() + employee_count.get("Driver") + 1;
                Driver new_driver = sf.makeDriver("driver_"+total_drivers);
                employee_count.merge("Driver", 1, Integer::sum);
                drivers.add(new_driver);
                HelperClass.wtf(fWriter,dealer_name + "Hired Driver "+new_driver.name);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Hired Driver "+new_driver.name);
                break;
        }
    }

    public void promote(String type){ //creates new Staff subtype and copies Intern's data into it
        //create Staff of given type and add to appropriate list (with same name that the Intern has)
        //remove the Intern
        //do not hire an intern right here; that is supposed to be done in the daily opening procedures
        // get name of intern being promoted, will be first intern of list
        String intern_name = interns.get(0).name;
        int intern_days_worked = interns.get(0).daysWorked;
        int total_intern_bonus = interns.get(0).totalBonus;
        int total_intern_pay = interns.get(0).totalPay;
        // remove intern from intern list
        interns.remove(0);
        // Drop number of interns by one
        employee_count.merge("Intern", -1, Integer::sum);
        switch(type){
            // if promoting to sales person
            case "Sales Person":
                // new sales person object with same intern name
                // added total pay, bonus, and days worked to new object
                SalesPerson new_sales_person = sf.makeSalesPerson(intern_name);
                new_sales_person.daysWorked = intern_days_worked;
                new_sales_person.totalBonus = total_intern_bonus;
                new_sales_person.totalPay = total_intern_pay;
                // increment hashmap value for total sales person
                employee_count.merge("Sales Person", 1, Integer::sum);
                sales_persons.add(new_sales_person);
                HelperClass.wtf(fWriter, dealer_name + "Promoted Intern "+ new_sales_person.name + " to Sales Person");
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Promoted Intern "+ new_sales_person.name + " to Sales Person");
                break;
            case "Mechanic":
                // new mechanic object with intern name
                // added total pay, bonus, and days worked to new object
                Mechanic new_mechanic = sf.makeMechanic(intern_name);
                new_mechanic.daysWorked = intern_days_worked;
                new_mechanic.totalBonus = total_intern_bonus;
                new_mechanic.totalPay = total_intern_pay;
                // increment mechanics hash total by one
                employee_count.merge("Mechanic", 1, Integer::sum);
                mechanics.add(new_mechanic);
                HelperClass.wtf(fWriter, dealer_name + "Promoted Intern "+ new_mechanic.name + " to Mechanic");
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Promoted Intern "+ new_mechanic.name + " to Mechanic");
                break;
        }
        // if we dropped total intern count under 3 due to promotions, hire more interns
        while(employee_count.get("Intern") < 3){
            hireEmployee("Intern");
        }
    }

    public void buyCar(String type){ //creates a Vehicle subclass of type and puchase it using budget
        //create a Vehicle object
        //randomize cleanliness (5% sparkling, 35% clean, 60% dirty)
        //randomize condition (all equal %)
        //randomize acquisition cost 
        //      Car: 10,000 - 20,000
        //      Truck: 10,000 - 40,000
        //      Performance Car: 20,000 - 40,000
        //      Motorcycle: 5,000 - 15,000
        //      Monster Truck: 50,000 - 100,000
        //      Electric Car: 40,000 - 80,000
        //sell price = 2 * acquisition cost 
        switch (type){
            case "Car":
                //add new Car with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_cars = inventory.get("Car").size() + sold_vehicles.get("Car").size() + 1;
                Car new_car = vf.makeCar("car_"+total_cars.toString());
                inventory.get("Car").add(new_car);
                operating_budget -= new_car.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+new_car.name+" at a cost of "+(int)new_car.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers( dealer_name + "Acquired "+new_car.name+" at a cost of "+(int)new_car.purchase_price);
                break;
            case "Truck":
                //add new Truck with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_trucks = inventory.get("Truck").size() + sold_vehicles.get("Truck").size() + 1;
                Truck new_truck = vf.makeTruck("truck_"+total_trucks.toString());
                inventory.get("Truck").add(new_truck);
                operating_budget -= new_truck.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+new_truck.name+" at a cost of "+(int)new_truck.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+new_truck.name+" at a cost of "+(int)new_truck.purchase_price);
                break;
            case "Performance Car":
                //add new perf car with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_performance = inventory.get("Performance Car").size() + sold_vehicles.get("Performance Car").size() + 1;
                PerformanceCar perf_car = vf.makePerformanceCar("performance_"+total_performance.toString());
                inventory.get("Performance Car").add(perf_car);
                operating_budget -= perf_car.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+perf_car.name+" at a cost of "+(int)perf_car.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+perf_car.name+" at a cost of "+(int)perf_car.purchase_price);
                break;
            case "Electric Car":
                //add new electric car with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_electric = inventory.get("Electric Car").size() + sold_vehicles.get("Electric Car").size() + 1;
                ElectricCar elect_car = vf.makeElectricCar("electric_car_"+total_electric.toString());
                inventory.get("Electric Car").add(elect_car);
                operating_budget -= elect_car.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+elect_car.name+" at a cost of "+(int)elect_car.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+elect_car.name+" at a cost of $"+(int)elect_car.purchase_price);
                break;
            case "Motorcycle":
                //add new motorcycle with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_motor = inventory.get("Motorcycle").size() + sold_vehicles.get("Motorcycle").size() + 1;
                Motorcycle motorcycle = vf.makeMotorcycle("motorcycle_"+total_motor.toString());
                inventory.get("Motorcycle").add(motorcycle);
                operating_budget -= motorcycle.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+motorcycle.name+" at a cost of "+(int)motorcycle.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+motorcycle.name+" at a cost of $"+(int)motorcycle.purchase_price);
                
                break;
            case "Monster Truck":
                //add new monster truck with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                String truck_name = HelperClass.getMonsterTruckName(inventory.get("Monster Truck"),sold_vehicles.get("Monster Truck"));
                MonsterTruck monster_t = vf.makeMonsterTruck(truck_name);
                inventory.get("Monster Truck").add(monster_t);
                operating_budget -= monster_t.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+monster_t.name+" at a cost of $"+(int)monster_t.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+monster_t.name+" at a cost of $"+(int)monster_t.purchase_price);
                break;
            case "Super Car":
                //add new monster truck with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_super = inventory.get("Super Car").size() + sold_vehicles.get("Super Car").size() + 1;
                SuperCar super_t = vf.makeSuperCar("super_car_"+total_super.toString());
                inventory.get("Super Car").add(super_t);
                operating_budget -= super_t.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+super_t.name+" at a cost of $"+(int)super_t.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+super_t.name+" at a cost of $"+(int)super_t.purchase_price);
                break;
            case "Classic Car":
                //add new monster truck with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_classic = inventory.get("Classic Car").size() + sold_vehicles.get("Classic Car").size() + 1;
                ClassicCar classic = vf.makeClassicCar("classic_car_"+total_classic.toString());
                inventory.get("Classic Car").add(classic);
                operating_budget -= classic.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+classic.name+" at a cost of $"+(int)classic.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+classic.name+" at a cost of $"+(int)classic.purchase_price);
                break;
            case "Muscle Car":
                //add new monster truck with randomized values (may need to add a constructor to Vehicle)
                //decrement operating budget by car's acquisition cost
                Integer total_muscle = inventory.get("Muscle Car").size() + sold_vehicles.get("Muscle Car").size() + 1;
                MuscleCar muscle = vf.makeMuscleCar("muscle_car_"+total_muscle.toString());
                inventory.get("Muscle Car").add(muscle);
                operating_budget -= muscle.purchase_price;
                HelperClass.wtf(fWriter, dealer_name + "Acquired "+muscle.name+" at a cost of $"+(int)muscle.purchase_price);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + "Acquired "+muscle.name+" at a cost of $"+(int)muscle.purchase_price);
                break;
                
                
        }
        checkBudget();
    }

    public void openDealership(int day){ //restock Interns and Vehicles,  declare # buyers
        checkBudget();
        daySalesTotal = 0;
        HelperClass.wtf(fWriter, dealer_name + "Opening the dealership...");
        // Create new list of buyers
        ArrayList<Buyer> tmp_buyers = new ArrayList<Buyer>();
        int dailyBuyers = 0;
        // if friday or saturday then 2-8 buyers
        if(day % 5 == 0 || day % 6 == 0){
            dailyBuyers = HelperClass.CreateRandomNumber(2, 8);
        }
        // else only 0-5 buyers
        else{
            dailyBuyers = HelperClass.CreateRandomNumber(0, 5);
        }
        // create buyer objects
        for(int i = 0; i < dailyBuyers;i++){
            Buyer tmpBuyer = new Buyer();
            tmp_buyers.add(tmpBuyer);
        }
        // customers list gets replaced by today's buyers
        customers = tmp_buyers;

        // verify we have 4 of each type of car or else buy more
        for(Entry<String, ArrayList<Vehicle>> entry: inventory.entrySet()) {
            while(entry.getValue().size() < 4){
                buyCar(entry.getKey());
            }
        }
        // Verify we have 3 of each employee or else hire
        for(Entry<String, Integer> entry: employee_count.entrySet()) {
            while(entry.getValue() < 3){
                hireEmployee(entry.getKey());
            }
        }
    }
        // washing of vehicles
    public void doWashing(){ //tells interns to wash vehicles 
        // go through all interns
        for(int i = 0; i < interns.size(); i++){
            Intern current_intern = interns.get(i);
            //adding a day of work
            // current_intern.daysWorked += 1;
            // randomize vehicle type to clean
            String type_to_clean_one = HelperClass.getRandomVehicleType();
            // get how many of that type of vehicle we have
            ArrayList<Vehicle> dirty = HelperClass.getVehiclesByCleanliness(0, inventory.get(type_to_clean_one));
            if(dirty.size() == 0){return;}
            // pick random vehicle from random type
            int pick_vehicle = HelperClass.CreateRandomNumber(0, dirty.size()-1);
            Vehicle v_one = dirty.get(pick_vehicle);
            // start with dirty
            IntIntTuple washing_results = current_intern.wash(v_one);
            String washType = HelperClass.getWashTypeString(washing_results.t);
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + current_intern.name + " washed " + v_one.name + " with " + washType + " and made it " + washing_results.i);
            // if dirty then clean it
            Integer new_cleanliness = washing_results.i;
            // Update vehicle condition 
            Integer new_condition = washing_results.b;
            // update cleanliness/condition level for vehicle
            v_one.cleanliness = new_cleanliness;
            v_one.condition = new_condition;
            // get washtype used for printing purposes
            // if sparkling then intern gets bonus
            if(v_one.cleanliness == 2){
                payBonus(v_one.clean_bonus, current_intern);
                HelperClass.wtf(fWriter, dealer_name + current_intern.name + " washed " + v_one.type +" "+ v_one.name + " and made it Sparkling (earned $" + (int)v_one.clean_bonus + " bonus) with " + washType);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + current_intern.name + " washed " + v_one.type +" "+ v_one.name + " and made it Sparkling (earned $" + (int)v_one.clean_bonus + " bonus) with " + washType);
            }
            // get second possible vehicle
            String type_to_clean_two = HelperClass.getRandomVehicleType();
            ArrayList<Vehicle> clean_veh = HelperClass.getVehiclesByCleanliness(1, inventory.get(type_to_clean_two));
            if(clean_veh.size() == 0){return;}
            int pick_sec_vehicle = HelperClass.CreateRandomNumber(0, clean_veh.size()-1);
            Vehicle v_two = clean_veh.get(pick_sec_vehicle);
            washing_results = current_intern.wash(v_two);
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + current_intern.name + " washed " + v_two.name + " with " + washType + " and made it " + washing_results.i);
            // continue to clean vehicle
            // if clean then clean it
            // wash vehicle and return new cleanliness level
            new_cleanliness = washing_results.i;
            new_condition = washing_results.b;
            // update cleanliness/condition level for vehicle
            v_two.cleanliness = new_cleanliness;
            v_two.condition = new_condition;
            // get bonus is sparkling
            // get washtype used for printing purposes
            washType = HelperClass.getWashTypeString(washing_results.t);
            if(v_two.cleanliness == 2){
                payBonus(v_two.clean_bonus, current_intern);
                HelperClass.wtf(fWriter, dealer_name + current_intern.name + " washed " + v_two.type +" "+ v_two.name + " and made it Sparkling (earned $" + (int)v_two.clean_bonus + " bonus) with " + washType);
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + current_intern.name + " washed " + v_two.type +" "+ v_two.name + " and made it Sparkling (earned $" + (int)v_two.clean_bonus + " bonus) with " + washType);
            }
        }
    }

    public void doRepairs(){ //tells mechanics to repair vehicles
        // go through all mechanics
        for(int i = 0; i < mechanics.size();i++){
            // get mechanic
            Mechanic current_mechanic = mechanics.get(i);
            current_mechanic.carsFixedToday = 0; //resetting each day
            // current_mechanic.daysWorked += 1;
            for(int j = 0; j < 2; j++){
                // get random vehicle type to fix
                String type_to_fix = HelperClass.getRandomVehicleType();
                // number of vehicles of that type
                int size_of_vehicles = inventory.get(type_to_fix).size();
                // pick first vehicle to fi
                int pick_vehicle = HelperClass.CreateRandomNumber(0, size_of_vehicles-1);
                Vehicle v_one = inventory.get(type_to_fix).get(pick_vehicle);
                // if Like new, don't try to fix it
                if(v_one.condition != 2){
                    // Fix car and return new condition
                    Integer new_condition = current_mechanic.fix(v_one);
                    //OBSERVER PATTERN
                    this.notifyObservers(dealer_name + current_mechanic.name + " fixed "+ v_one.name +" and made it "+ HelperClass.getConditionString(new_condition));
                    // if mechanic improved condition, give bonus
                    if(new_condition > v_one.condition){
                        // increase price based on condition increase
                        if(new_condition == 2){v_one.sell_price *= 1.25;}
                        if(new_condition == 1){v_one.sell_price *= 1.5;}
                        // if now like new and electric vehicle then increase range by 100
                        if(new_condition == 2 && v_one.type == "Electric Car"){v_one.range += 100;}
                        String condition_string = HelperClass.getConditionString(new_condition);
                        payBonus(v_one.fix_bonus, current_mechanic);
                        HelperClass.wtf(fWriter, dealer_name + current_mechanic.name + " fixed " + v_one.type +" "+ v_one.name + " and made it " + condition_string + "(earned $" + (int)v_one.fix_bonus + " bonus)");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + current_mechanic.name + " fixed " + v_one.type +" "+ v_one.name + " and made it " + condition_string + "(earned $" + (int)v_one.fix_bonus + " bonus)");
                    }
                    // update vehicle condition
                    v_one.condition = new_condition;
                }
            }
        }
    }

    public void doSelling(){ //attempt to sell to each Buyer
        for(int i = 0; i < customers.size(); i++){
            int rand_index = HelperClass.CreateRandomNumber(0, sales_persons.size()-1);
            SalesPerson currentSalesPerson = sales_persons.get(rand_index);
            Buyer currentBuyer = customers.get(i);
            Vehicle v = currentSalesPerson.sell(currentBuyer, inventory, vf);
            if(!v.type.equals("DEFAULT")){
                inventory.get(v.type).remove(v);
                double og_sell_price = v.sell_price;
                // IMPLEMENTATION OF OUR DECORATOR PATTERN
                if(currentBuyer.extended_warranty){
                    v = new ExtendedWarrantyVehicle(v);
                    HelperClass.wtf(fWriter, dealer_name + "The customer added an Extended Warranty to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    //OBSERVER PATTERN
                    this.notifyObservers(dealer_name + "The customer added an Extended Warranty to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    og_sell_price = v.sell_price;
                }
                if(currentBuyer.sat_radio){
                    v = new SatelliteRadioVehicle(v);
                    HelperClass.wtf(fWriter, dealer_name + "The customer added a Satellite Radio to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    //OBSERVER PATTERN
                    this.notifyObservers(dealer_name + "The customer added a Satellite Radio to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    og_sell_price = v.sell_price;
                }
                if(currentBuyer.undercoating){
                    v = new UndercoatingVehicle(v);
                    HelperClass.wtf(fWriter, dealer_name + "The customer added an Undercoating to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    //OBSERVER PATTERN
                    this.notifyObservers(dealer_name + "The customer added an Undercoating to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    og_sell_price = v.sell_price;
                }
                if(currentBuyer.road_rescue){
                    v = new RoadRescueCoverageVehicle(v);
                    HelperClass.wtf(fWriter, dealer_name + "The customer added Road Rescue Coverage to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    //OBSERVER PATTERN
                    this.notifyObservers(dealer_name + "The customer added Road Rescue Coverage to "+v.name+", increasing the price from "+ (int)og_sell_price + " to "+(int)v.sell_price);
                    og_sell_price = v.sell_price;
                }
                operating_budget += v.sell_price;
                daySalesTotal += v.sell_price;
                sold_vehicles.get(v.type).add(v);
                payBonus(v.sell_bonus, currentSalesPerson);
                HelperClass.wtf(fWriter, dealer_name + currentSalesPerson.name + " sold " + v.type + " "+ v.name + " to Buyer for $ "+ (int)v.sell_price+" (earned $" + (int)v.sell_bonus + " bonus)");
                //OBSERVER PATTERN
                this.notifyObservers(dealer_name + currentSalesPerson.name + " sold " + v.type + " "+ v.name + " to Buyer for $ "+ (int)v.sell_price+" (earned $" + (int)v.sell_bonus + " bonus)");
            }
        }
    }

    public void doQuitting(){ //remove employees if they quit
        // probability people quit
        double intern_quit = Math.random();
        double sales_persons_quit = Math.random();
        double mechanic_quit = Math.random();

        // if they do quit add them to departed staff
        // remove them from staff list
        // print who quit
        // update their employed status 
        if(intern_quit < .10){
            interns.get(0).isEmployed = false;
            departed_staff.get("Intern").add(interns.get(0));
            HelperClass.wtf(fWriter, dealer_name + "Intern " + interns.get(0).name + " quit.");
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + "Intern " + interns.get(0).name + " quit.");;
            interns.remove(0);
            employee_count.merge("Intern", -1, Integer::sum);
        }
        if(sales_persons_quit < .10){
            sales_persons.get(0).isEmployed = false;
            departed_staff.get("Sales Person").add(sales_persons.get(0));
            HelperClass.wtf(fWriter, dealer_name + "Sales Person " + sales_persons.get(0).name + " quit.");
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + "Sales Person " + sales_persons.get(0).name + " quit.");
            sales_persons.remove(0);
            employee_count.merge("Sales Person", -1, Integer::sum);
        }
        if(mechanic_quit < .10){
            mechanics.get(0).isEmployed = false;
            departed_staff.get("Mechanic").add(mechanics.get(0));
            HelperClass.wtf(fWriter, dealer_name + "Mechanic " + mechanics.get(0).name + " quit.");
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + "Mechanic " + mechanics.get(0).name + " quit.");
            mechanics.remove(0);
            employee_count.merge("Mechanic", -1, Integer::sum);
        }
    }

    public void doRacing(){//races with a randomly chosen type of vehicle (also fires injured drivers)
        /*
         * select vehicle type
         * choose 3 of the given type (or as much as the dealership has, if less than 3)
         *      if we have 0, we do not participate in the race
         *      assign a Driver to each vehicle 
         * randomize finish position of all FNCD participating vehicles
         *      1st, 2nd, 3rd are 'winners'; 16th, 17th, 18th, 19th, 20th are 'Damaged'
         * for vehicles that have won a race, sell price increases 10% (does not stack for multiple wins) and Driver gets a bonus
         * 'damaged' vehicles are set to Broken condition an the driver has 30% chance of becoming injured
         * announce outcomes in output File
         */
        int rand = HelperClass.CreateRandomNumber(1, 5); //selecting vehicle type 
        
        int numberRaceable = 0;
        int tempPlace = HelperClass.CreateRandomNumber(0, 20);
        ArrayList<Integer> takenPlaces = new ArrayList<Integer>(20);
        
        switch (rand){
            case 1:{ //Truck
                //match (x <= 3) vehicles to drivers 
                numberRaceable = HelperClass.getNumRaceable(inventory.get("Truck"));
                ArrayList<VehicleDriverTuple> pairs = HelperClass.matchDriversToVehicles(numberRaceable, inventory.get("Truck"), drivers, vf);
                for( VehicleDriverTuple pair : pairs){
                    //randomize finishing position for each pair
                    while (takenPlaces.contains(tempPlace)){
                        tempPlace = HelperClass.CreateRandomNumber(0, 20);
                    }
                    if(tempPlace <= 3){
                        //if place in top 3...
                        HelperClass.wtf(fWriter,dealer_name +  pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        pair.d.getBonus(100);
                        pair.d.racesWon++;
                        if(pair.v.races_won > 0){
                            pair.v.races_won++;
                        }else{
                            pair.v.races_won++;
                            pair.v.sell_price = pair.v.sell_price * 1.1;
                        }
                    } else if(tempPlace >= 16){
                        //if place in bottom 5...
                        //log event to File
                        HelperClass.wtf(fWriter,dealer_name +  pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //check injury
                        if(Math.random() < 0.3){
                            pair.d.isEmployed = false;
                            departed_staff.get("Driver").add(pair.d);
                            HelperClass.wtf(fWriter, dealer_name + "Driver " + pair.d.name + " got injured.");
                            //OBSERVER PATTERN
                            this.notifyObservers( dealer_name + "Driver " + pair.d.name + " got injured.");
                            drivers.remove(drivers.indexOf(pair.d));
                            employee_count.merge("Driver", -1, Integer::sum);
                        }
                        //car gets damaged
                        pair.v.condition = 0;
                    } else {
                        //if not winner or loser, just print results
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                    }
                    takenPlaces.add(tempPlace);
                }
                break;
            }
                
            case 2: {//PerformanceCar
                //match (x <= 3) vehicles to drivers 
                numberRaceable = HelperClass.getNumRaceable(inventory.get("Performance Car"));
                ArrayList<VehicleDriverTuple> pairs = HelperClass.matchDriversToVehicles(numberRaceable, inventory.get("Performance Car"), drivers, vf);
                for( VehicleDriverTuple pair : pairs){
                    //randomize finishing position for each pair
                    while (takenPlaces.contains(tempPlace)){
                        tempPlace = HelperClass.CreateRandomNumber(0, 20);
                    }
                    if(tempPlace <= 3){
                        //if place in top 3...
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        pair.d.getBonus(100);
                        pair.d.racesWon++;
                        if(pair.v.races_won > 0){
                            pair.v.races_won++;
                        }else{
                            pair.v.races_won++;
                            pair.v.sell_price = pair.v.sell_price * 1.1;
                        }
                    } else if(tempPlace >= 16){
                        //if place in bottom 5...
                        //log event to File
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //check injury
                        if(Math.random() < 0.3){
                            pair.d.isEmployed = false;
                            departed_staff.get("Driver").add(pair.d);
                            HelperClass.wtf(fWriter, dealer_name + "Driver " + pair.d.name + " got injured.");
                            this.notifyObservers( dealer_name + "Driver " + pair.d.name + " got injured.");
                            drivers.remove(drivers.indexOf(pair.d));
                            employee_count.merge("Driver", -1, Integer::sum);
                        }
                        //car gets damaged
                        pair.v.condition = 0;
                    } else {
                        //if not winner or loser, just print results
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                    }
                    takenPlaces.add(tempPlace);
                }
                break;
            }
            case 3:{ //Motorcycle
                //match (x <= 3) vehicles to drivers 
                numberRaceable = HelperClass.getNumRaceable(inventory.get("Motorcycle"));
                ArrayList<VehicleDriverTuple> pairs = HelperClass.matchDriversToVehicles(numberRaceable, inventory.get("Motorcycle"), drivers, vf);
                for( VehicleDriverTuple pair : pairs){
                    //randomize finishing position for each pair
                    while (takenPlaces.contains(tempPlace)){
                        tempPlace = HelperClass.CreateRandomNumber(0, 20);
                    }
                    if(tempPlace <= 3){
                        //if place in top 3...
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        pair.d.getBonus(100);
                        pair.d.racesWon++;
                        if(pair.v.races_won > 0){
                            pair.v.races_won++;
                        }else{
                            pair.v.races_won++;
                            pair.v.sell_price = pair.v.sell_price * 1.1;
                        }
                    } else if(tempPlace >= 16){
                        //if place in bottom 5...
                        //log event to File
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //check injury
                        if(Math.random() < 0.3){
                            pair.d.isEmployed = false;
                            departed_staff.get("Driver").add(pair.d);
                            HelperClass.wtf(fWriter, dealer_name + "Driver " + pair.d.name + " got injured.");
                            this.notifyObservers(dealer_name + "Driver " + pair.d.name + " got injured.");
                            drivers.remove(drivers.indexOf(pair.d));
                            employee_count.merge("Driver", -1, Integer::sum);
                        }
                        //car gets damaged
                        pair.v.condition = 0;
                    } else {
                        //if not winner or loser, just print results
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                    }
                    takenPlaces.add(tempPlace);
                }
                break;
            }
            case 4:{//MonsterTruck 
                //match (x <= 3) vehicles to drivers 
                numberRaceable = HelperClass.getNumRaceable(inventory.get("Monster Truck"));
                ArrayList<VehicleDriverTuple> pairs = HelperClass.matchDriversToVehicles(numberRaceable, inventory.get("Monster Truck"), drivers, vf);
                for( VehicleDriverTuple pair : pairs){
                    //randomize finishing position for each pair
                    while (takenPlaces.contains(tempPlace)){
                        tempPlace = HelperClass.CreateRandomNumber(0, 20);
                    }
                    if(tempPlace <= 3){
                        //if place in top 3...
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        pair.d.getBonus(100);
                        pair.d.racesWon++;
                        if(pair.v.races_won > 0){
                            pair.v.races_won++;
                        }else{
                            pair.v.races_won++;
                            pair.v.sell_price = pair.v.sell_price * 1.1;
                        }
                    } else if(tempPlace >= 16){
                        //if place in bottom 5...
                        //log event to File
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //check injury
                        if(Math.random() < 0.3){
                            pair.d.isEmployed = false;
                            departed_staff.get("Driver").add(pair.d);
                            HelperClass.wtf(fWriter, dealer_name + "Driver " + pair.d.name + " got injured.");
                            this.notifyObservers(dealer_name + "Driver " + pair.d.name + " got injured.");
                            drivers.remove(drivers.indexOf(pair.d));
                            employee_count.merge("Driver", -1, Integer::sum);
                        }
                        //car gets damaged
                        pair.v.condition = 0;
                    } else {
                        //if not winner or loser, just print results
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                    }
                    takenPlaces.add(tempPlace);
                }
                break;
            }
            case 5:{ //Muscle Car
                //match (x <= 3) vehicles to drivers 
                numberRaceable = HelperClass.getNumRaceable(inventory.get("Muscle Car"));
                ArrayList<VehicleDriverTuple> pairs = HelperClass.matchDriversToVehicles(numberRaceable, inventory.get("Muscle Car"), drivers, vf);
                for( VehicleDriverTuple pair : pairs){
                    //randomize finishing position for each pair
                    while (takenPlaces.contains(tempPlace)){
                        tempPlace = HelperClass.CreateRandomNumber(0, 20);
                    }
                    if(tempPlace <= 3){
                        //if place in top 3...
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" won the race in position "+tempPlace+"!");
                        pair.d.getBonus(100);
                        pair.d.racesWon++;
                        if(pair.v.races_won > 0){
                            pair.v.races_won++;
                        }else{
                            pair.v.races_won++;
                            pair.v.sell_price = pair.v.sell_price * 1.1;
                        }
                    } else if(tempPlace >= 16){
                        //if place in bottom 5...
                        //log event to File
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" and "+pair.v.name+" lost the race and finished in position "+tempPlace+".");
                        //check injury
                        if(Math.random() < 0.3){
                            pair.d.isEmployed = false;
                            departed_staff.get("Driver").add(pair.d);
                            HelperClass.wtf(fWriter, dealer_name + "Driver " + pair.d.name + " got injured.");
                            this.notifyObservers(dealer_name + "Driver " + pair.d.name + " got injured.");
                            drivers.remove(drivers.indexOf(pair.d));
                            employee_count.merge("Driver", -1, Integer::sum);
                        }
                        //car gets damaged
                        pair.v.condition = 0;
                    } else {
                        //if not winner or loser, just print results
                        HelperClass.wtf(fWriter, dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                        //OBSERVER PATTERN
                        this.notifyObservers(dealer_name + pair.d.name+" drove "+pair.v.name+" in the race and finished in position "+tempPlace+".");
                    }
                    takenPlaces.add(tempPlace);
                }
                break;
            }
        }
    }

    public void endDay(){ //increment days worked, does quitting
        // pay interns
        for(int i = 0; i < interns.size(); i++){
            Intern current_intern = interns.get(i);
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + current_intern.name + " got paid $" + current_intern.dailyPay);
            paySalary(current_intern);
            current_intern.daysWorked++;
        }
        // pay mechanics
        for(int i = 0; i < mechanics.size();i++){
            Mechanic currentMechanic = mechanics.get(i);
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + currentMechanic.name +" got paid $" + currentMechanic.dailyPay);
            paySalary(currentMechanic);
            currentMechanic.daysWorked++;
        }
        // pay sales people
        for(int i = 0; i< sales_persons.size();i++){
            SalesPerson currentSalesPerson = sales_persons.get(i);
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + currentSalesPerson.name + " got paid $" + currentSalesPerson.dailyPay);
            paySalary(currentSalesPerson);
            currentSalesPerson.daysWorked++;
        }
        //pay Drivers
        for(int i = 0; i<drivers.size(); i++){
            Driver currentDriver = drivers.get(i);
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + currentDriver.name + " got paid $" + currentDriver.dailyPay);
            paySalary(currentDriver);
            currentDriver.daysWorked++;
        }
        
    }

    private void checkBudget(){ //checks if budget fell below 0
        //call this every time the op. budget is decremented (except in constructor)
        while(this.operating_budget < 0){
            operating_budget += 250000;
            bail_out_total += 250000;
            //the professor wants us to announce this event
            HelperClass.wtf(fWriter, dealer_name + "Operating budget below $0, adding bail out money. Bail out total: $" + (int)bail_out_total);
            //OBSERVER PATTERN
            this.notifyObservers(dealer_name + "Operating budget below $0, adding bail out money. Bail out total: $" + (int)bail_out_total);
        }
    }

    public void printTabular(){ //logs daily info in a table (per writeup)
        //desired output:
        //STAFF - names - days worked - total normal pay - total bonus pay - working/quit
        //INVENTORY - name, cost, sales price, condition, cleanliness, sold/in stock
        //TOTAL OPERATING BUDGET
        //TOTAL SALES FOR THE DAY
        HelperClass.wtf(fWriter, "\n");
        HelperClass.wtf(fWriter, "PRINT TABULAR FOR " + dealer_name);
        HelperClass.wtf(fWriter, "-----------------------------------------------------------------------------------------------------------\n");
        Object[] employeeHeader = new String[] {"EMPLOYEE", "DAYS WORKED", "TOTAL NORMAL PAY", "TOTAL BONUS PAY", "EMPLOYED"};
        Object[] inventoryHeader = new String[] {"VEHICLE", "COST", "SALE PRICE", "CONDITION", "CLEANLINESS","RACES WON", "SOLD"};
        Object[] temp;

        //first section header
        HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", employeeHeader));

        //log info for interns, sales_persons, mechanics
        for(Intern i: interns){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "Yes"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }
        for(Staff i: departed_staff.get("Intern")){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "No"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }

        for(SalesPerson i: sales_persons){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "Yes"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }
        for(Staff i: departed_staff.get("Sales Person")){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "No"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }

        for(Mechanic i: mechanics){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "Yes"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }
        for(Staff i: departed_staff.get("Mechanic")){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "No"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }

        for(Driver i: drivers){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "Yes"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }
        for(Staff i: departed_staff.get("Driver")){
            temp = new Object[] {i.name, i.daysWorked, i.totalPay, i.totalBonus, "No"};
            HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s", temp));
        }

        //second section header
        HelperClass.wtf(fWriter, "\n");
        HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s%-22s%-22s", inventoryHeader));
        //log sold cars, trucks, performance cars
        //- name, cost, sales price, condition, cleanliness, sold/in stock
        for(Entry<String, ArrayList<Vehicle>> entry: inventory.entrySet()) {
            for(Vehicle v: entry.getValue()){
                temp = new Object[] {v.name, v.purchase_price, (int)v.sell_price, HelperClass.COtoStr(v.condition), HelperClass.CLtoStr(v.cleanliness), v.races_won, "In Stock"};
                HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s%-22s%-22s", temp));
            }
        }

        for(Entry<String, ArrayList<Vehicle>> entry: sold_vehicles.entrySet()) {
            for(Vehicle v: entry.getValue()){
                temp = new Object[] {v.name, v.purchase_price, (int)v.sell_price, HelperClass.COtoStr(v.condition), HelperClass.CLtoStr(v.cleanliness),v.races_won, "Sold"};
                HelperClass.wtf(fWriter, String.format("%-22s%-22s%-22s%-22s%-22s%-22s%-22s", temp));
            }
        }
        
        //spacing so it looks nicer
        HelperClass.wtf(fWriter, "\n");
        //TOTAL OPERATING BUDGET
        HelperClass.wtf(fWriter, "Operating Budget: "+(int)this.operating_budget);
        //TOTAL SALES FOR THE DAY
        HelperClass.wtf(fWriter, "Total sales today: "+(int)this.daySalesTotal);

        HelperClass.wtf(fWriter, "\n");
    }


    // NO LONGER BEING USED
    public void simulate(int days){ //does daily procedures for number of days
        //no need for other params because each instance of FNCD sets values in the constructor
        //announce beginning of simulation
        HelperClass.wtf(fWriter, "------------------------------------- BEGIN SIMULATION ----------------------------------------------");
        int weeks = 0;
        int dayOfWeek = 1;

        for(int i = 1; i <= days; i++){
            weeks = i/7;
            logr = Logger.getLoggerInstance(i);
            //all the daily activities mentioned in the PDF -- we may want to create private functions for these 
            //open
            openDealership(i);
            //washing
            doWashing();
            //repairing
            doRepairs();
            //selling
            doSelling();
            // if the day is a race day, then race
            dayOfWeek = i - (weeks*7);
            if(dayOfWeek%3 == 0 || dayOfWeek%7 == 0 ){
                doRacing();
            }
            //ending
            endDay();
            logr.closeFW();
            trakr.print(this);
        }
        //announce end of simulation 
        HelperClass.wtf(fWriter, "------------------------------------- END OF SIMULATION ----------------------------------------------\n");
        // close tracker.txt file
        trakr.closeFW();
        try{
            fWriter.close();
        } catch (IOException e){
            System.out.println("Error closing FileWriter.");
        }
        System.out.println("Done.");
    }
}
