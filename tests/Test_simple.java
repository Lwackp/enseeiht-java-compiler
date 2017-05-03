interface Machine {
    int getId();
}

interface Avion extends Machine {
    int getAltitude();
}

interface Bateau extends Machine {
    boolean isTitanic(int id_titanic);
}

class Hydravion implements Avion, Bateau {
    int altitude;
    int id = 42;

    public Hydravion(int _altitude, int _id) {
        this.altitude = _altitude;
        this.id = _id;
    }

    public int getId() {
        return id;
    }

    public int getAltitude() {
        return this.altitude;
    }

    public boolean isTitanic(int id_titanic) {
        if (this.id == id_titanic) {
            return true;
        }
        return false;
    }
}

public class Test_simple {
    public static void main(String[] args) {
        int i = 2000;
        Hydravion ha_jaune = new Hydravion(i+2000, 0);

        Bateau bateau = ha_jaune;

        print(bateau.isTitanic(1));
    }
}