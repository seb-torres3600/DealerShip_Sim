public class GetTime implements Command{
    private FNCD dealer;

    public GetTime(FNCD dealership){
        this.dealer = dealership;
    }

    @Override
    public void execute(){
        dealer.GetTime();
    }

}