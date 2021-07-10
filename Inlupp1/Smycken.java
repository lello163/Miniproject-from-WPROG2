class Smycken extends Värdesaker {
    private int adelstenar;
    private boolean material;

    public Smycken(String name, int adelstenar, boolean material) {
        super(name);
        this.adelstenar = adelstenar;
        this.material = material;
    }

    public int getAdelstenar() {
        return adelstenar;
    }

    public boolean isGold() {
        return material;
    }

    public double getVärde() {
        return (((material) ? 2000 : 700) + 500 * adelstenar);
    }

    public String toString() {
        String str = "Smyckes" + super.toString() + " " + "Ädelstenar: " + adelstenar;
        if (material)
            str += " är Guld";
        return str;
    }
    }
