public class VehicleDriverTuple {
    public Driver d;
    public Vehicle v;
    // vehicle driver tuple to return paired vehicle and driver objects together
    //used primarily for racedays, where outcomes are the same for the Driver and the Vehicle they drive
    public VehicleDriverTuple(Vehicle ToBecomeV, Driver toBecomeD){
        d = toBecomeD;
        v = ToBecomeV;
    }
}
