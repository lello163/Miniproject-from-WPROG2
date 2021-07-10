abstract class Värdesaker {
    private String name;

    public Värdesaker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;

    }

    public double getMoms() {
        return 1.25 * getVärde();
    }

    abstract public double getVärde();

    public String toString() {
        return "namn: " + name + " " + "Moms: " + getMoms();
    }
}
