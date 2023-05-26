public class VehicleFactory {
    public VehicleFactory(){
        //nothing needed here
    }
    
    public Car makeCar(String name){
        return new Car(name);
    }

    public Truck makeTruck(String name){
        return new Truck(name);
    }

    public PerformanceCar makePerformanceCar(String name){
        return new PerformanceCar(name);
    }

    public MonsterTruck makeMonsterTruck(String name){
        return new MonsterTruck(name);
    }

    public Motorcycle makeMotorcycle(String name){
        return new Motorcycle(name);
    }

    public ElectricCar makeElectricCar(String name){
        return new ElectricCar(name);
    }

    public SuperCar makeSuperCar(String name){
        return new SuperCar(name);
    }

    public MuscleCar makeMuscleCar(String name){
        return new MuscleCar(name);
    }

    public ClassicCar makeClassicCar(String name){
        return new ClassicCar(name);
    }

    public DefaultVehicle makeDefaultVehicle(){
        return new DefaultVehicle();
    }
}
