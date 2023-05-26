import java.util.Scanner;

public class GetDetailsOnItem implements Command{
    private FNCD dealer;
    private Scanner input;

    public GetDetailsOnItem(FNCD d, Scanner in){
        this.dealer = d;
        this.input = in;
    }

    public void execute(){dealer.GetDetailsOnItem(input);}

}