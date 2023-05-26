public class ExtendedWarrantyVehicle extends VehicleDecorator {
    public ExtendedWarrantyVehicle(Vehicle toBecomeV){
        super(toBecomeV);
        this.sell_price = this.sell_price*1.2; //mutate Vehicle's price as described in writeup 
    }

    
    // public double getSellPrice(){
    //     return this.v.sell_price * 1.2; //never used
    // }

}
