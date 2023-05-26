public class Intern extends Staff{
    
    public int carsWashedToday;
    public int typeOfWash;
    protected WashBehavior wb;

    public Intern(){
        super();
        this.dailyPay = 100;
        switch (HelperClass.CreateRandomNumber(1, 3)){
            // IMPLEMENTATION OF OUR STRATEGY PATTERN
            case 1: wb = new ElbowGreaseWash();
            case 2: wb = new ChemicalWash();
            case 3: wb = new DetailedWash();
        }
    }

    public Intern(String toBecomeName){
        super(toBecomeName);
        this.dailyPay = 100;
        switch (HelperClass.CreateRandomNumber(1, 3)){
            // IMPLEMENTATION OF OUR STRATEGY PATTERN
            case 1: wb = new ElbowGreaseWash();
            case 2: wb = new ChemicalWash();
            case 3: wb = new DetailedWash();
        }
    }

    public IntIntTuple wash(Vehicle v){ //returns new cleanliness value for the Vehicle and a boolean for side effects happening
        // THIS IMPLEMENTATION ASSUMES 'SPARKLING' CARS WILL NOT BE INPUT
        //it also assumes that Interns will not be asked to wash more than 2 cars per day (taken care of in FNCD)
        return wb.wash(v);
    }
}
