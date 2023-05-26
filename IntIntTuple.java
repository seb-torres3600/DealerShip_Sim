public class IntIntTuple {
    // i for cleanliness
    public int i;
    // b for condition
    public int b;
    // t for wash type
    public int t;

    public IntIntTuple(){
        i = -1;
        b = -1;
        t = -1;
    }

    public IntIntTuple(int toBecomeI, int toBecomeB,int toBecomet){
        i = toBecomeI;
        b = toBecomeB;
        t = toBecomet;
    }
}