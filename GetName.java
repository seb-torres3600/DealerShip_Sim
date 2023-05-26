public class GetName implements Command{
    private FNCD dealer;
    public GetName(FNCD dealership){
        this.dealer = dealership;
    }

    @Override
    public void execute(){
        dealer.GetName();
    }
    
}