public class ChemicalWash implements WashBehavior{
    @Override
    public IntIntTuple wash(Vehicle v){
        //wash with bleach procedure
        //dirty -> 10% dirty, 80% clean, 10% sparkling
        //clean -> 10% dirty, 70% clean, 20% sparkling
        //*** in all cases 10% chance of vehicle becoming broken
        double randResult = Math.random(); 
        boolean broken = (Math.random() < 0.10);
        int breaks = v.condition;
        if(broken){
            breaks = 0;
        }
        int retVal = -1;
        switch (v.cleanliness){
            case 0: //dirty
                if(randResult < 0.10){
                    retVal= 0;
                } else if(randResult < 0.90){
                    retVal= 1;
                }else{
                    retVal= 2;
                }
            case 1: //clean
                if(randResult < 0.10){
                    retVal= 0;
                } else if(randResult < 0.80){
                    retVal= 1;
                }else{
                    retVal= 2;
                }
            case 3: //sparking***should not be here
                return new IntIntTuple(-1, -1,2);
        }
        return new IntIntTuple(retVal, breaks,2);
    }
}
