public class PerformanceCar extends Vehicle {
    
    public PerformanceCar(String new_name){
        this.name = new_name;
        // Add type as string for printing purposes
        this.type = "Performance Car";
        // Purchase price of a performance car must be between $20k and $40k
        this.purchase_price = HelperClass.CreateRandomNumber(20000, 40000);
        // Sell price is twice purchase price
        this.sell_price = purchase_price * 2;
        // Condition of car from 0 to 3
        this.condition = HelperClass.CreateRandomNumber(0,2);
        // Bonuses for things that can be done to the cars
        this.fix_bonus = 500;
        this.clean_bonus = 50;
        this.sell_bonus = 1000;

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
