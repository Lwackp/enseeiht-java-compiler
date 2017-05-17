class Triplet {
    private int x;
    private int y;
    private int z;

    public Triplet(int _x, int _y, int _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public Triplet(int _x, int _y) {
        x = _x;
        y = _y;
        z = 0;
    }

    public Triplet(int _x) {
        x = _x;
        y = 0;
        z = 0;
    }

    public Triplet() {
        x = 0;
        y = 0;
        z = 0;
    }

    public int getFirst() {
        return x;
    }
    
    public int getSecond() {
        return y;
    }
    
    public int getThird() {
        return z;
    }
}

public class Test_multiple_constructors {
    public static void main(String[] args) {
        Triplet _triplet3 = new Triplet(1, 2, 3);
        println _triplet3.getFirst();
        println _triplet3.getSecond();
        println _triplet3.getThird();
        Triplet _triplet2 = new Triplet(4, 5);
        println _triplet2.getFirst();
        println _triplet2.getSecond();
        println _triplet2.getThird();
        Triplet _triplet1 = new Triplet(6);
        println _triplet1.getFirst();
        println _triplet1.getSecond();
        println _triplet1.getThird();
        Triplet _triplet0 = new Triplet(); 
        println _triplet0.getFirst();
        println _triplet0.getSecond();
        println _triplet0.getThird();
    }
}
