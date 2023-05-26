public class SwitchSalesPerson implements Command{
    private FNCD dealer;

    public SwitchSalesPerson(FNCD dealership){
        this.dealer = dealership;
    }

    @Override
    public void execute(){
        dealer.SwitchSalesPerson();
    }

}