public class RoadRescueCoverageVehicle extends VehicleDecorator{
    public RoadRescueCoverageVehicle(Vehicle toBecomeV){
        super(toBecomeV);
        this.sell_price = this.sell_price*1.02;
    }

    public double getSellPrice(){
        return this.v.sell_price * 1.02;
        //5% chance of buyer adding this
    }
}
