public class DefaultStaff extends Staff {
    public DefaultStaff(){
        super();
        isEmployed = false; 
        daysWorked = -1;
        totalPay = -1;
        dailyPay = -1; 
        totalBonus = -1;
        name = "DEFAULT";
        salary = -1; 
    }

    @Override
    public Boolean isDefaultStaff(){
        return true;
    }
}
