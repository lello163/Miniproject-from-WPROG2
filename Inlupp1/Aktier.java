class Aktier extends Värdesaker {
    private int antal;
    private int kurs;

    public Aktier(String name, int antal, int kurs) {
        super(name);
        this.antal = antal;
        this.kurs = kurs;
    }

    public int getAntal() {
        return antal;

    }

    public double getKurs() {
        return kurs;
    }
    public void setkurs(int newKurs){
        this.kurs = newKurs;
    }


    public double getVärde() {
            return antal * kurs;
        }
    public String toString() {
        return "Aktie" + super.toString() + " "+ "Antal: " + antal + " "+ "Kurs: " + kurs;
    }
}
