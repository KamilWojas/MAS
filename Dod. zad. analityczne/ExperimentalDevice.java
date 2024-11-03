public class ExperimentalDevice extends Machine{

    int levelOfSecret=10;

    public ExperimentalDevice( int power, int levelOfSecret) {
        super("Redacted", power);
        this.levelOfSecret = levelOfSecret;
    }

    public ExperimentalDevice(int levelOfSecret) {
        this.levelOfSecret = levelOfSecret;
    }

    @Override
    public String getName() {
        return "?";
    }

    @Override
    public String toString() {
        return super.toString() + "-SECRETLVL" + levelOfSecret;
    }
}
