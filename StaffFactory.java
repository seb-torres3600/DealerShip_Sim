public class StaffFactory {
    public StaffFactory(){
        //nothing needed here
    }
    // intern
    public Intern makeIntern(String name){
        return new Intern(name);
    }

    public SalesPerson makeSalesPerson(String name){
        return new SalesPerson(name);
    }

    public Mechanic makeMechanic(String name){
        return new Mechanic(name);
    }

    public Driver makeDriver(String name){
        return new Driver(name);
    }
    
    public Staff makeDefaultStaff(){
        return new DefaultStaff();
    }
}
