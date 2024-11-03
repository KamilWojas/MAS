public class Car extends Machine{

    private int numberOfTires;

    public Car(int numberOfTires) {
        super();
        this.numberOfTires = numberOfTires;
    }

    public Car(String name, int power, int numberOfTires) {
        super(name, power);
        this.numberOfTires = numberOfTires;
    }

    @Override
    void setPower(int i) {
        if (i<getPower()){
            System.out.print("You can't set less power than before!");
        }else {
            super.setPower(i);
        }
    }

    public void setNumberOfTires(int numberOfTires) {
        this.numberOfTires = numberOfTires;
    }

    public int getNumberOfTires() {
        return numberOfTires;
    }
}
