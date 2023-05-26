import java.util.Scanner;

// Controller for command pattern
// switch statement to set new command
public class Controller{
    private Command command;
    public void setCommand(String comm, FNCD c_dealer,Scanner in){
        
        Command new_comm = new CommandError(); 
        switch(comm){
            case "1":
                new_comm = new GetName(c_dealer);
                break;
            case "2":
                new_comm = new GetTime(c_dealer);
                break;
            case "3":
                new_comm = new SwitchSalesPerson(c_dealer);
                break;
            case "4":
                new_comm = new GetInventory(c_dealer);
                break;
            case "5":
                // System.out.println("HERE1");
                new_comm = new GetDetailsOnItem(c_dealer,in);
                break;
            case "6":
                new_comm = new BuyVehicle(c_dealer,in);
                break;
            case "8":
                new_comm = new ExitProgram();
                break;
            default: 
                // System.out.println("TESTING");
                break;

        }
        this.command = new_comm;
        

    }
    public void executeCommand(){
        command.execute();
    }
}