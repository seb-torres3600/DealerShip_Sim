public class Mechanic extends Staff{
    public int carsFixedToday;

    public Mechanic(){
        super();
        carsFixedToday = 0;
        this.dailyPay = 800;
    }

    public Mechanic(String toBecomeName){
        super(toBecomeName);
        carsFixedToday = 0;
        this.dailyPay = 800;
    }

    public int fix(Vehicle v){ //THIS IMPLEMENTATION ASSUMES NO 'Like-new' VEHICLES ARE INPUT
        if(carsFixedToday < 2){
            double randomChance = Math.random(); //range is 0.0 - 1.0 
            if(v.cleanliness > 0){ //fixing always dirties the vehicle
                v.cleanliness -= 1; 
            } 
            carsFixedToday += 1;
            if(randomChance >= 0.20){ //80% chance of fixing for all vehicles
                return v.condition+1;
                //FNCD should call getBonus() if this is returned
            } else{
                return v.condition;
            }
        } else{
            return -1; //already fixed 2 cars, not fixing another
        }
    }//end fix function
}
