
class Main {
  public static void main(String[] args) {
    // create dealership object
    FNCD dealership_north = new FNCD("SimResults","DEALER NORTH: ");
    FNCD dealership_south = new FNCD("SimResults","DEALER SOUTH: ");
    // Using Helper class simulate to be able to run two dealerships concurrently 
    // Tried multi threading but we have a lot of possible race conditions and it would
    // severly complicate things 
    HelperClass.simulate(dealership_north, dealership_south, 31);
  }
}
