public class SatelliteRadioVehicle extends VehicleDecorator{
    public SatelliteRadioVehicle(Vehicle toBecomeV){
        super(toBecomeV);
        this.sell_price = this.sell_price*1.05; // increase sell price by 5% 
    }

    // public double getSellPrice(){
    //     return this.v.sell_price * 1.05; //never used
    //     //40% chance of buyer adding this
    // }
}

