import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Tracker implements Observer{
    // Singleton Implementation
    // Eager
    private int total_profit_north = 0;
    private int total_pay_north = 0;
    private int total_profit_south = 0;
    private int total_pay_south = 0;
    public String day;
    private File outputFile;
    private FileWriter fWriter;
    private String outputFileName = "Tracker.txt";
    private static Tracker tracker = new Tracker();

    private Tracker(){
        outputFile = new File(outputFileName);
        // create new file for tracker logger
        try{
            if(outputFile.createNewFile()){
                System.out.println("Output file created: "+outputFile.getName());
            } else {
                System.out.println("File already exists: "+outputFile.getName());
            }
        } catch(IOException e){
            System.out.println("An error occurred creating the output text file.");
        }
        //initialize the FileWriter to use
        try{
            fWriter = new FileWriter(outputFileName);
            System.out.println("Initialized the FileWriter.");
        } catch (IOException e){
            System.out.println("An error occurred initializing the FileWriter.");
        }
    }

    public static Tracker getTrackerInstance(){
        return tracker;
    }

    @Override
    public void update(String msg){
        // string parsing to get needed info to print to tracker file and simresults
        String new_msg = msg;
        // string all characters and just get numbers
        String numberOnly = new_msg.replaceAll("[^0-9]", " ");
        // remove trailing or leading empty space
        numberOnly = numberOnly.trim();
        // remove all continous empty space in string 
        numberOnly = numberOnly.replaceAll(" +", " ");
        // split from string to array
        String[] num = numberOnly.split(" ");
        // if array contains sold add cost to total profit
        if(msg.contains("sold")){
            int cost = -1;
            for(String n: num){
                int tmp = Integer.parseInt(n);
                // highest number will be the sell price
                if(tmp > cost){
                    cost = tmp;
                }
            }
            if(msg.contains("NORTH")){
                total_profit_north += cost;
            }
            else{
                total_profit_south += cost;
            }
        }
        // if we bought a vehicle, highest value is pruchase price
        // subtract from total earned 
        if(msg.contains("Acquired")){
            int cost = -1;
            for(String n: num){
                int tmp = Integer.parseInt(n);
                if(tmp > cost){
                    cost = tmp;
                }
            }
            if(msg.contains("NORTH")){
                total_profit_north -= cost;
            }
            else{
                total_profit_south -= cost;
            }
        }
        // add bonus to total staff pay
        if(msg.contains("bonus")){
            int bonus_min = 15;
            int bonus_max = 1000;
            int bonus = 0;
            // no bonus under 15 and none above 1000
            // done to avoid getting car#
            for(String n: num){
                int tmp = Integer.parseInt(n);
                if(tmp > bonus_min && tmp < bonus_max){
                    bonus = tmp;
                }
            }
            if(msg.contains("NORTH")){
                total_pay_north += bonus;
            }
            else{
                total_pay_south += bonus;
            }    
        }
        // add total staff pay
        if(msg.contains("paid")){
            int pay = -1;
            // highest number in paid string is the dailyPay
            for(String n: num){
                int tmp = Integer.parseInt(n);
                if(tmp > pay){
                    pay = tmp;
                }
            }
            if(msg.contains("NORTH")){
                total_pay_north += pay;
            }
            else{
                total_pay_south += pay;
            }    
        }
        // get the day for printing reasons
        if(msg.contains("Day")){
            day = numberOnly;
        }
    }
    
    public void print(FNCD dealer){
        try{
            int total_pay;
            int total_profit;
            if(dealer.dealer_name.equals("DEALER NORTH: ")){
                total_pay = total_pay_north;
                total_profit = total_profit_north;
            }
            else{
                total_pay = total_pay_south;
                total_profit = total_profit_south;
            }

            // write to tracker.txt
            fWriter.write("----------"+dealer.dealer_name+"------------\n");
            fWriter.write("Tracker: Day " + day + "\n");
            fWriter.write("Total money earned by all staff: " + total_pay+ "\n");
            fWriter.write("Total money earned by the FNCD: " + total_profit+ "\n");
            fWriter.write("\n\n"); //adding another \n here cuz SimResults doesnt show the extra space (feel free to remove if thats just my machine being weird)
            // write to simresults.txt
            dealer.fWriter.write("----------"+dealer.dealer_name+"------------\n");
            dealer.fWriter.write("Tracker: Day " + day + "\n");
            dealer.fWriter.write("Total money earned by all staff: " + total_pay+ "\n");
            dealer.fWriter.write("Total money earned by the FNCD: " + total_profit+ "\n");
            dealer.fWriter.write("\n\n");
        }
        catch (IOException e){
            System.out.println("writing error in Tracker");
        }
    }

    public void closeFW(){
        try{
            fWriter.close();
        } catch (IOException e){
            System.out.println("Error closing FileWriter in Tracker.");
        }
    }
}