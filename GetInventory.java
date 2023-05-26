public class GetInventory implements Command{
    private FNCD dealer;

    public GetInventory(FNCD d){
        this.dealer = d;
    }

    public void execute(){
        dealer.GetInventory();
    }

    public int execute(String ve_name){
        return -1;
    }

}