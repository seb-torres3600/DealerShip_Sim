public class VehicleDecorator extends Vehicle {
    /*
     * Each type of add-on to the purchases (extended warranty, undercoating,
     * road rescue coverage, satellite radio) must be made as a class that extends 
     * VehicleDecorator, and have a Vehicle as a data member.
     */

     public Vehicle v;

     public VehicleDecorator(Vehicle toBecomeV){ 
        super(toBecomeV);
        v = toBecomeV;
     }
}
