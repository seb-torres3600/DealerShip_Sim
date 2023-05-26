import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Logger implements Observer{
    //writes to "logger-n"
    private int day;
    private File outputFile;
    private FileWriter fWriter;
    private String outputFileName;
    private static Logger logger;

    private Logger(int dayNo){
        day = dayNo;
        if(day < 1){
            return;
        }
        outputFileName = "Logger-"+day+".txt";
        
        outputFile = new File(outputFileName);
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

    public static Logger getLoggerInstance(int dayNo){
        if(logger == null){
            logger = new Logger(dayNo);
        }
        return logger;
    }

    @Override
    public void update(String msg){
        try{
            fWriter.write(msg+"\n");
        } catch (IOException e){
            System.out.println("writing error in Logger");
        }
    }

    public void closeFW(){
        try{
            fWriter.close();
            logger = null;
        } catch (IOException e){
            System.out.println("Error closing FileWriter in Logger.");
        }
    }
}
