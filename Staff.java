public abstract class Staff{
    public Boolean isEmployed;
    public int daysWorked;
    public int totalPay;
    public int dailyPay;
    public int totalBonus;
    public String name;
    public int salary;

    public Staff(){
        isEmployed = true; //we can change this later but I assume this is applicable when we hire an intern
        daysWorked = 0;
        totalPay = 0;
        dailyPay = 1; //we get to decide this value
        totalBonus = 0;
        name = "NAME_NOT_SET";
        salary = 1; //we get to decide this value
    }

    public Staff(String toBecomeName){ //additional constructor in case we want to name them this way
        isEmployed = true; 
        daysWorked = 0;
        totalPay = 0;
        dailyPay = 1; 
        totalBonus = 0;
        name = toBecomeName;
        salary = 1; 
    }

    public int getBonus(int amount){ //staffer reveives the bonus and returns total bonuses
        totalBonus += amount;
        return totalBonus;
    }

    public int getSalary(){ //I forget what we are using this for..
        totalPay += dailyPay;
        return totalPay;
    }

    public Boolean isDefaultStaff(){
        return false;
    }
}