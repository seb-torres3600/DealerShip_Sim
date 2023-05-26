public abstract class Vehicle {

    public String name;
    public int cleanliness;
    public int fix_bonus;
    public int clean_bonus;
    public int sell_bonus;
    public int condition;
    public String type;
    public int purchase_price;
    public double sell_price;
    public int races_won;
    // add range and engine size for new cars
    public int range;
    public int engine_size;
    // constructor
    public Vehicle(){
        name = "Unknown Name";
        cleanliness = 0;
        fix_bonus = 0;
        clean_bonus = 0;
        sell_bonus = 0;
        condition = 0;
        type = "Unknown";
        purchase_price = 0;
        sell_price = 0;
        races_won = 0;
    }

    // constructor for when using decorators
    public Vehicle(Vehicle v){
         name = v.name;
         cleanliness = v.cleanliness;
         fix_bonus = v.fix_bonus;
         clean_bonus = v.clean_bonus;
         sell_bonus = v.sell_bonus;
         condition = v.condition;
         type = v.type;
         purchase_price = v.purchase_price;
         sell_price = v.sell_price;
         races_won = v.races_won;
         switch(type){
            case "Electric Car": 
                range = v.range;
                break;
            case "Motorcycle":
                engine_size = v.engine_size;
                break;
         }
    }

    public boolean isDefaultVehicle(){
        return false;
    }
}
