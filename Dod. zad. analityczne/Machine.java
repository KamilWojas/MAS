public class Machine {

    String name;
    private int power;

    public Machine(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public Machine(){
        this.name = "Unknown Machine";
        this.power=10;
    };

    @Override
    public String toString() {
        return "I am the Machine: " + this.name + " with " + this.power + " power!";
    }

    void setPower(int i){
        System.out.println(i);
        this.power=i;
    }

    public int getPower() {

        return power;
    }

    public String getName() {
        return name;
    }
}
