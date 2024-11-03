public class RaceCar extends Car{

    public RaceCar(int numberOfTires) {
        super(numberOfTires);
    }

    public RaceCar(String name, int power, int numberOfTires) {
        super(name, power, numberOfTires);
    }

    @Override
    public String getName() {
        return "Supercool "+super.getName();
    }

    @Override
    public int getPower() {
        return 10*super.getPower();
    }

    @Override
    public void setNumberOfTires(int numberOfTires) {
        if (numberOfTires != getNumberOfTires()){
            System.out.println("Bad Idea");
        }

        super.setNumberOfTires(numberOfTires);
    }

    @Override
    public String toString() {
        return "Superfast " + getName();
    }
}
