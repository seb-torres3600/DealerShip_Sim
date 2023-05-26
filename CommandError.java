// Command for when the given command isn't recognized
public class CommandError implements Command{
    @Override
    public void execute() {
        System.out.println("No recognized command given");
    }
}