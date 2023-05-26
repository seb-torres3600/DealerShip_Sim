public class DetailedWash implements WashBehavior{
    public IntIntTuple wash(Vehicle v){
        //detailed wash procedure
        //dirty -> 20% dirty, 60% clean, 20% sparkling
        //clean -> 5% dirty, 55% clean, 40% sparkling
        double randResult = Math.random();
        // condition use to be false cause no change
        int condition = v.condition;
        switch (v.cleanliness){
            case 0: //dirty
                if(randResult < 0.20){
                    return new IntIntTuple(0, condition,3);
                } else if(randResult < 0.80){
                    return new IntIntTuple(1, condition,3);
                }else{
                    return new IntIntTuple(2,condition,3);
                }
            case 1: //clean
                if(randResult < 0.05){
                    return new IntIntTuple(0, condition,3);
                } else if(randResult < 0.60){
                    return new IntIntTuple(1, condition,3);
                }else{
                    return new IntIntTuple(2, condition,3);
                }
            case 3: //sparking***should not be here
                return new IntIntTuple();
        }
        return new IntIntTuple(); //should not reach this point
    }
}
