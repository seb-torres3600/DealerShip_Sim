public class MonsterTruck extends Vehicle {
    
    public MonsterTruck(String new_name){
        this.name = new_name;
        // Type of vehicle for printing purposes
        this.type = "Monster Truck";
        // Purchase price of truck from $50k to $100k 
        this.purchase_price = HelperClass.CreateRandomNumber(50000, 100000);
        // Sell price is twice purchase price
        this.sell_price = purchase_price * 2;
        // Level of cleanliness and condition from 0 to 2
        this.condition = HelperClass.CreateRandomNumber(0,2);
        // Bonuses for services
        this.fix_bonus = 300;
        this.clean_bonus = 40;
        this.sell_bonus = 600;
    
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
