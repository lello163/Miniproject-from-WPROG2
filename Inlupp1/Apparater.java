class Apparater extends Värdesaker {
    private int inköpspris;
    private int slitage;

    public Apparater(String name, int inköpspris, int slitage) {
        super(name);
        this.inköpspris = inköpspris;
        this.slitage = slitage;

    }

    public int getInköpspris() {
        return inköpspris;
    }

    public int getSlitage() {
        return slitage;
        }

    public double getVärde() {
        if (slitage == 10) {
            return slitage = inköpspris;
        } else {
            return (slitage / 10) * inköpspris;
        }
    }
    public String toString() {
        return "Apparats" + super.toString() + " "+ "inköpspris" + " "+ inköpspris +" "+ "slitage" + " "+ slitage;
    }
}
