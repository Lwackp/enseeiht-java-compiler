interface Machine {
    int getId();
}

interface Avion extends Machine {
    int getAltitude();
}

interface Bateau extends Machine {
    int isTitanic(int id_titanic);
}

class Hydravion implements Avion, Bateau {
    private int altitude;
    private int id;

    public Hydravion(int _altitude, int _id) {
        altitude = _altitude;
        id = _id;
    }

    public int getId() {
        return id;
    }

    public int getAltitude() {
        return altitude;
    }

    public int isTitanic(int id_titanic) {
        if (id != id_titanic) {
            return 0;
        }
        return 1;
    }
}

public class Test_simple {
    public static void main(String[] args) {
        int i = 2000;

        Hydravion ha_jaune = new Hydravion(i+2000, 42);

        Bateau bateau = ha_jaune;

        print bateau.isTitanic(1);
    }
}