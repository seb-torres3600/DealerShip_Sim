public class Motorcycle extends Vehicle {
    public Motorcycle(String new_car){
        this.name = new_car;
        // String type for printing purposes
        this.type = "Motorcycle";
        // Pruchase price from $5k to $15k
        this.purchase_price = HelperClass.CreateRandomNumber(5000, 15000);
        // Sell price twice the purchase price
        this.sell_price = purchase_price * 2;
        // Condition from 0 to 2
        this.condition = HelperClass.CreateRandomNumber(0,2);
        // Bonus values for services to car
        this.fix_bonus = 100;
        this.clean_bonus = 20;
        this.sell_bonus = 200;
        // Engine size 
        this.engine_size = HelperClass.engine_size();
        // Condition of car affects the sale price
        // If broken price is reduced by 50%
        // If used price reduced by 20%
        if(condition == 0){this.sell_price *= 0.5;}
        if(condition == 1){this.sell_price *= 0.8;}

        // Randomize starting cleanliness
        // 5% chance of sparkling
        // 35% chance of clean
        // 60% chance of dirty
        double rand_cleanliness = Math.random();
        if(rand_cleanliness < 0.05){this.cleanliness = 2;}
        else if(rand_cleanliness < 0.35){this.cleanliness = 1;}
        else{this.cleanliness = 0;}
    }
}  
