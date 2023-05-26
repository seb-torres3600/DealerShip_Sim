public class UndercoatingVehicle extends VehicleDecorator{
    public UndercoatingVehicle(Vehicle toBecomeV){ //basic constructor
        super(toBecomeV);
        this.sell_price = this.sell_price*1.05; // increase sell price by 5%
    }

    // public double getSellPrice(){
    //     return this.v.sell_price * 1.05; // never used
    //     //10% of buyer adding this
    // }
}
