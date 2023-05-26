public class Driver extends Staff {
    public int racesWon = 0;

    public Driver(String toBecomeName){ //basic constructor
        super(toBecomeName);
        this.dailyPay = 1000;
    }

    public Driver(){ //for cases where we need a default driver variable
        super();
    }
}
