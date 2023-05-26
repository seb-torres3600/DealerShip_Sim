public class DefaultVehicle extends Vehicle{
    // Vehicle is an interface now
    // So we can instanciate it 
    // This is our new default vehicle class
    public DefaultVehicle(){
        name = "DEFAULTVEHICLE";
        cleanliness = -1;
        fix_bonus = -1;
        clean_bonus = -1;
        sell_bonus = -1;
        condition = -1;
        type = "DEFAULT";
        purchase_price = -1;
        sell_price = -1;
        races_won = -1;
        range = -1;
        engine_size = -1;
    }

    @Override
    public boolean isDefaultVehicle(){
        return true;
    }
}
