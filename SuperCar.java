
public class SuperCar extends Vehicle {

    public SuperCar(String new_car){
        this.name = new_car;
        // String type for printing purposes
        this.type = "Super Car";
        // Pruchase price from $10k to $20k
        this.purchase_price = HelperClass.CreateRandomNumber(100000, 250000);
        // Sell price twice the purchase price
        this.sell_price = purchase_price * 2;
        // Condition from 0 to 2
        this.condition = HelperClass.CreateRandomNumber(0,2);
        // Bonus values for services to car
        this.fix_bonus = 10000;
        this.clean_bonus = 500;
        this.sell_bonus = 5000;

        
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
