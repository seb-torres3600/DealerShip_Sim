
public class ClassicCar extends Vehicle {

    public ClassicCar(String new_car){
        this.name = new_car;
        // String type for printing purposes
        this.type = "Classic Car";
        // Pruchase price from $5k to $80k
        this.purchase_price = HelperClass.CreateRandomNumber(5000, 80000);
        // Sell price twice the purchase price
        this.sell_price = purchase_price * 2;
        // Condition from 0 to 2
        this.condition = HelperClass.CreateRandomNumber(0,2);
        // Bonus values for services to car
        this.fix_bonus = 1000;
        this.clean_bonus = 50;
        this.sell_bonus = 500;

        
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
