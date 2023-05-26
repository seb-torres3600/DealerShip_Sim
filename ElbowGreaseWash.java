public class ElbowGreaseWash implements WashBehavior{
    public IntIntTuple wash(Vehicle v){
        //elbow grease wash procedure
        //dirty -> 25% dirty, 70% clean, 5% sparkling
        //clean -> 15% dirty, 70% clean, 15% sparkling
        //*** in all cases, 10% chance of making the vehicle like-new
        boolean becomesLikeNew = (Math.random() < 0.10);
        int condition = v.condition;
        if(becomesLikeNew){
            condition = 2;
        }
        int retVal = -1;
        double randResult = Math.random(); 
        switch (v.cleanliness){
            case 0: //dirty
                if(randResult < 0.25){
                    retVal = 0;
                } else if(randResult < 0.95){
                    retVal =1;
                }else{
                    retVal = 2;
                }
            case 1: //clean
                if(randResult < 0.15){
                    retVal = 0;
                } else if(randResult < 0.85){
                    retVal = 1;
                }else{
                    retVal = 2;
                }
            case 3: //sparking***should not reach here
                return new IntIntTuple();
        }
        return new IntIntTuple(retVal, condition,1); 
    }
}
